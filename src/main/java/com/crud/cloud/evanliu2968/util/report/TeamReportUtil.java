package com.crud.cloud.evanliu2968.util.report;

import com.crud.cloud.evanliu2968.constant.EvaluationTypeEnum;
import com.crud.cloud.evanliu2968.constant.TeamCharColorEnum;
import com.crud.cloud.evanliu2968.util.CollectionsUtil;
import com.crud.cloud.evanliu2968.util.NumberUtil;
import com.crud.cloud.evanliu2968.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class TeamReportUtil {

    public static final  BigDecimal Point75Division = new BigDecimal(75);
    public static final  BigDecimal Point25Division = new BigDecimal(25);
    //左小号
    public static final String leftThan = "(";
    //左等号
    public static final String leftEqual = "[";
    //右小号
    public static final String rightThan = ")";
    //右等号
    public static final String rightEqual = "]";
    //用于分数比较时比较精确度
    public static final double accuracy = 0.0001;

    public static Logger log = LoggerFactory.getLogger(TeamReportUtil.class);

    /**
     * 根据团队报告类型得出统计图数据
     * @param userIndexScores
     * @param evaluationType
     * @return
     */
    public static ReportResult getTeamReportChartDataByType(Map<Integer, LinkedHashMap<String, BigDecimal>> userIndexScores, EvaluationTypeEnum evaluationType){
        ReportResult result=new ReportResult();
        result.setReportScore(new ArrayList<>());
        List<BigDecimal> pointDivisions=Arrays.asList(Point25Division, Point75Division);
        LinkedHashMap<String,Map<Integer,BigDecimal>> indexUserScores=toIndexUserScoreMap(userIndexScores);
        List<String> orderIndexNames=new ArrayList<>();
        for(LinkedHashMap<String, BigDecimal> map : userIndexScores.values()){
            for(String index:map.keySet()){
                orderIndexNames.add(index);
            }
        }

        for (String indexName : indexUserScores.keySet()) {

            Map<Integer, BigDecimal> userScoreMap = indexUserScores.get(indexName);
            List<BigDecimal> scores=new ArrayList<>();
            for (Map.Entry<Integer, BigDecimal> entry : userScoreMap.entrySet()) {
                scores.add(entry.getValue());
            }
            List<BigDecimal> divisionScores=culPointDivision(scores,pointDivisions,1);

            ReportScore reportScore=new ReportScore();
            reportScore.setIndexName(indexName);
            reportScore.setAvgScore(getAverageScoreByList(scores,1));
            reportScore.setDivision25Score(divisionScores.get(0));
            reportScore.setDivision75Score(divisionScores.get(1));
            result.getReportScore().add(reportScore);
        }
        getMapOrderToList(result.getReportScore(),orderIndexNames);

        //如果是能力倾向有结论
        if(evaluationType.getValue().equals("ability")){
            getTeamAbilityResult(result);
            result.setProportion(getTeamAbilitiyProportion(result,indexUserScores));
        }
        //计算大五占比
        if(evaluationType.getValue().equals("bigFive")){
            result.setProportion(getTeamBigFiveProportion(result,indexUserScores));
        }

        //计算工作环占比
        if(evaluationType.getValue().equals("WORKINGRING")){
            result.setProportion(getTeamWorkingRingProportion(result,indexUserScores));
        }

        //计算商推、认知占比
        if(evaluationType.getValue().equals("businessReason") || evaluationType.getValue().equals("occupationalCognitive")){
            result.setProportion(getTeamBusinessReasonProportion(result,indexUserScores));
        }

        //计算驱动力占比
        if(evaluationType.getValue().equals("iDriver")){
            getTeamIDriverProportion(result,userIndexScores,indexUserScores);
        }

        //计算任职风险高风险比例
        if(evaluationType.getValue().equals("jobRisk")){
            getJobRiskProportion(result,indexUserScores);
        }

        return  result;
    }

    /**
     * 指标按传过来排序
     * @param reportScores
     * @param orderIndexNames
     */
    public static  void getMapOrderToList(List<ReportScore> reportScores,List<String> orderIndexNames){
        List<ReportScore> reportScoreTmp=new ArrayList<>();
        for(String r2:orderIndexNames){
            for(ReportScore r1 : reportScores){
                if(r2.equals(r1.getIndexName())){
                    reportScoreTmp.add(r1);
                    break;
                }
            }
        }
        reportScores=reportScoreTmp;
    }

    /**
     * 获取360能力的图例定义
     * @return
     */
    public static List<IntervalDef> getAppraise360IntervalDefs(){
        List<IntervalDef> intervalDefs=new ArrayList<>();
        IntervalDef intervalDef1=new IntervalDef();
        intervalDef1.setResult("能力");
        intervalDef1.setColorStyle(TeamCharColorEnum.T3652b5.name());
        intervalDefs.add(intervalDef1);

        IntervalDef intervalDef2=new IntervalDef();
        intervalDef2.setResult("团队他评");
        intervalDef2.setColorStyle(TeamCharColorEnum.T6999f9.name());
        intervalDefs.add(intervalDef2);

        IntervalDef intervalDef3=new IntervalDef();
        intervalDef3.setResult("团队他评相对优势");
        intervalDef3.setColorStyle(TeamCharColorEnum.T00b0f0.name());
        intervalDefs.add(intervalDef3);

        IntervalDef intervalDef4=new IntervalDef();
        intervalDef4.setResult("团队他评相对劣势");
        intervalDef4.setColorStyle(TeamCharColorEnum.Tfb767c.name());
        intervalDefs.add(intervalDef4);

        IntervalDef intervalDef5=new IntervalDef();
        intervalDef5.setResult("团队自评");
        intervalDef5.setColorStyle(TeamCharColorEnum.Tf6b436.name());
        intervalDefs.add(intervalDef5);

        IntervalDef intervalDef6=new IntervalDef();
        intervalDef6.setResult("团队他评75分位值");
        intervalDef6.setColorStyle(TeamCharColorEnum.T707070.name());
        intervalDefs.add(intervalDef6);

        IntervalDef intervalDef7=new IntervalDef();
        intervalDef7.setResult("团队他评25分位值");
        intervalDef7.setColorStyle(TeamCharColorEnum.T707070.name());
        intervalDefs.add(intervalDef7);

        return  intervalDefs;
    }

    /**
     * 设置360能力评估他评的指标优劣势
     * @param indexOtherAppraiseScores
     * @param advantageNum
     * @param disadvantageNum
     */
    public static void getOtherAppraise360CharStyle(List<ReportScore> indexOtherAppraiseScores,Integer advantageNum,Integer disadvantageNum){
        List<Appraise360IndexStyle> indexStyles=new ArrayList<>();
        List<ReportScore> levelTwoIndexOtherAppraiseScores=new ArrayList<>();//二级指标的平均分
        for(ReportScore reportScore : indexOtherAppraiseScores) {
            Appraise360IndexStyle indexStyle0=new Appraise360IndexStyle();
            indexStyle0.setColorStyle(TeamCharColorEnum.T3652b5.name()); //他评的颜色都是T6999f9
            if(reportScore.getIndexName().equals("能力")){  //一级指标
                reportScore.setAppraise360IndexStyle(indexStyle0);
            }else{
                indexStyle0.setColorStyle(TeamCharColorEnum.T6999f9.name()); //他评的颜色都是T6999f9
                reportScore.setAppraise360IndexStyle(indexStyle0);
                levelTwoIndexOtherAppraiseScores.add(reportScore);
            }
        }
        //升序排列
        levelTwoIndexOtherAppraiseScores=levelTwoIndexOtherAppraiseScores.stream().filter(m->!StringUtil.isEmpty(m.getAvgScore())).sorted(Comparator.comparing(ReportScore::getAvgScore)).collect(Collectors.toList());
        for(int i=0;i<disadvantageNum;i++){
            Appraise360IndexStyle indexStyle1=new Appraise360IndexStyle();
            indexStyle1.setColorStyle(TeamCharColorEnum.T6999f9.name()); //他评的颜色都是T6999f9
            indexStyle1.setAdvantageInfo("DISADVANTAGE");
            levelTwoIndexOtherAppraiseScores.get(i).setAppraise360IndexStyle(indexStyle1);
        }

        //降序排列
        levelTwoIndexOtherAppraiseScores=levelTwoIndexOtherAppraiseScores.stream().filter(m->!StringUtil.isEmpty(m.getAvgScore())).sorted(Comparator.comparing(ReportScore::getAvgScore).reversed()).collect(Collectors.toList());
        for(int i=0;i<advantageNum;i++){
            Appraise360IndexStyle indexStyle2=new Appraise360IndexStyle();
            indexStyle2.setColorStyle(TeamCharColorEnum.T6999f9.name());
            indexStyle2.setAdvantageInfo("ADVANTAGE");
            levelTwoIndexOtherAppraiseScores.get(i).setAppraise360IndexStyle(indexStyle2);
        }
        Integer indexSize=levelTwoIndexOtherAppraiseScores.size();
        for(int i=1;i<indexSize+1;i++){
            for(int j=0;i<indexSize;i++){
                if(indexOtherAppraiseScores.get(i).getIndexName().equals(levelTwoIndexOtherAppraiseScores.get(j).getIndexName())){
                    indexOtherAppraiseScores.get(i).setAppraise360IndexStyle(levelTwoIndexOtherAppraiseScores.get(j).getAppraise360IndexStyle());
                }
            }
        }
    }

    /**
     * 人才盘点九宫格（潜能：待发展、成熟、优势  绩效：待提升、达标、优秀 ）
     * @param userAblilityProformanceLevels
     * @return
     */
    public static Map<String,Proportion> getTalentInventoryNineGrid(Map<Integer, Map<String, String>> userAblilityProformanceLevels){
        Integer userSize=userAblilityProformanceLevels.size();

        String aLowerLevel="待发展";
        String aMidumLevel="成熟";
        String aHighLevel="优势";
        String pLowerLevel="待提升";
        String pMidumLevel="达标";
        String pHighLevel="优秀";

        String nineGrid1="瘦狗";
        String nineGrid2="蚌珠_ampl";
        String nineGrid3="蚌珠_ahpl";
        String nineGrid4="金牛_alpm";
        String nineGrid5="金牛_ampm";
        String nineGrid6="明星_ahpm";
        String nineGrid7="金牛_alph";
        String nineGrid8="明星_amph";
        String nineGrid9="明星+";

        Map<String,Proportion> ablilityProformanceProportion=new HashMap<>();
        for(Integer userId : userAblilityProformanceLevels.keySet()) {

            Map<String,String> ablilityProformanceLevel = userAblilityProformanceLevels.get(userId);
            String ablilityLevel="";
            String proformanceLevel="";

            getLevelByAblilityProformanceName(ablilityLevel, proformanceLevel, ablilityProformanceLevel);

            Proportion proportion=new Proportion();
            proportion.setUserIds(new ArrayList<>());

            if(ablilityLevel.equals(aLowerLevel) && proformanceLevel.equals(pLowerLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("待提升/待发展");
                proportion.setText(nineGrid1);
                if(!ablilityProformanceProportion.containsKey(nineGrid1)){
                    ablilityProformanceProportion.put(nineGrid1,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid1).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aMidumLevel) && proformanceLevel.equals(pLowerLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("待提升/成熟");
                proportion.setText(nineGrid2);
                if(!ablilityProformanceProportion.containsKey(nineGrid2)){
                    ablilityProformanceProportion.put(nineGrid2,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid2).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aHighLevel) && proformanceLevel.equals(pLowerLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("待提升/优势");
                proportion.setText(nineGrid3);
                if(!ablilityProformanceProportion.containsKey(nineGrid3)){
                    ablilityProformanceProportion.put(nineGrid3,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid3).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aLowerLevel) && proformanceLevel.equals(pMidumLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("达标/待发展");
                proportion.setText(nineGrid4);
                if(!ablilityProformanceProportion.containsKey(nineGrid4)){
                    ablilityProformanceProportion.put(nineGrid4,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid4).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aMidumLevel) && proformanceLevel.equals(pMidumLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("达标/成熟");
                proportion.setText(nineGrid5);
                if(!ablilityProformanceProportion.containsKey(nineGrid5)){
                    ablilityProformanceProportion.put(nineGrid5,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid5).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aHighLevel) && proformanceLevel.equals(pMidumLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("达标/优势");
                proportion.setText(nineGrid6);
                if(!ablilityProformanceProportion.containsKey(nineGrid6)){
                    ablilityProformanceProportion.put(nineGrid6,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid6).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aLowerLevel) && proformanceLevel.equals(pHighLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("优秀/待发展");
                proportion.setText(nineGrid7);
                if(!ablilityProformanceProportion.containsKey(nineGrid7)){
                    ablilityProformanceProportion.put(nineGrid7,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid7).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aMidumLevel) && proformanceLevel.equals(pHighLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("优秀/成熟");
                proportion.setText(nineGrid8);
                if(!ablilityProformanceProportion.containsKey(nineGrid8)){
                    ablilityProformanceProportion.put(nineGrid8,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid8).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(aHighLevel) && proformanceLevel.equals(pHighLevel)){
                proportion.getUserIds().add(userId);
                proportion.setInteval("优秀/优势");
                proportion.setText(nineGrid9);
                if(!ablilityProformanceProportion.containsKey(nineGrid9)){
                    ablilityProformanceProportion.put(nineGrid9,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid9).getUserIds().add(userId);
                }
            }
        }

        Proportion tmp=new Proportion();
        tmp.setValue(BigDecimal.ZERO);
        tmp.setUserIds(new ArrayList<>());
        tmp.setPercentage("0%");
        if(ablilityProformanceProportion.get(nineGrid1)!=null){
            ablilityProformanceProportion.get(nineGrid1).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid1).getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid1).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid1).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid1,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid2)!=null){
            ablilityProformanceProportion.get(nineGrid2).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid2).getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid2).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid2).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid2,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid3)!=null) {
            ablilityProformanceProportion.get(nineGrid3).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid3).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid3).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid3).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid3,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid4)!=null) {
            ablilityProformanceProportion.get(nineGrid4).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid4).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid4).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid4).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid4,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid5)!=null) {
            ablilityProformanceProportion.get(nineGrid5).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid5).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid5).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid5).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid5,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid6)!=null) {
            ablilityProformanceProportion.get(nineGrid6).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid6).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid6).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid6).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid6,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid7)!=null) {
            ablilityProformanceProportion.get(nineGrid7).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid7).getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid7).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid7).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid7,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid8)!=null) {
            ablilityProformanceProportion.get(nineGrid8).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid8).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid8).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid8).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid8,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid9)!=null) {
            ablilityProformanceProportion.get(nineGrid9).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid9).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid9).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid9).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid9,tmp);
        }

        return ablilityProformanceProportion;
    }

    public static void getLevelByAblilityProformanceName(String ablilityLevel, String proformanceLevel,
                                                    Map<String,String> ablilityProformanceLevel){
        for (String ablilityProformanceName : ablilityProformanceLevel.keySet()){
            if(ablilityProformanceName.equals("ability")){
                ablilityLevel=ablilityProformanceLevel.get(ablilityProformanceName);
            }
            if(ablilityProformanceName.equals("PERFORMANCE")){
                proformanceLevel=ablilityProformanceLevel.get(ablilityProformanceName);
            }
        }
    }

    /**
     * 能力绩效九宫格（能力：低、中、高  绩效：低、中、高 ）
     * @param userAblilityProformanceLevels
     * @return
     */
    public static Map<String,Proportion>  getAbilityAndPerformanceNineGrid(Map<Integer, Map<String, String>> userAblilityProformanceLevels){
                                                                            //用户  潜能（个人能力倾向）绩效
        Integer userSize=userAblilityProformanceLevels.size();
        String lowerLevel="低";
        String midumLevel="中";
        String highLevel="高";

        String nineGrid11="潜力者";
        String nineGrid12="才华初露者";
        String nineGrid13="超级明星";
        String nineGrid21="差距员工";
        String nineGrid22="中坚力量";
        String nineGrid23="能力之星";
        String nineGrid31="问题员工";
        String nineGrid32="基本胜任";
        String nineGrid33="熟练员工";

        Map<String,Proportion> ablilityProformanceProportion=new HashMap<>();
        for(Integer userId : userAblilityProformanceLevels.keySet()) {
            Map<String,String> ablilityProformanceLevel = userAblilityProformanceLevels.get(userId);
            String ablilityLevel="";
            String proformanceLevel="";

            getLevelByAblilityProformanceName(ablilityLevel, proformanceLevel, ablilityProformanceLevel);
            Proportion proportion=new Proportion();
            proportion.setUserIds(new ArrayList<>());
            if(ablilityLevel.equals(lowerLevel) && proformanceLevel.equals(lowerLevel)){
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid11)){
                    ablilityProformanceProportion.put(nineGrid11,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid11).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(lowerLevel) && proformanceLevel.equals(midumLevel)){
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid12)){
                    ablilityProformanceProportion.put(nineGrid12,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid12).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(lowerLevel) && proformanceLevel.equals(highLevel)){
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid13)){
                    ablilityProformanceProportion.put(nineGrid13,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid13).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(midumLevel) && proformanceLevel.equals(lowerLevel)){
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid21)){
                    ablilityProformanceProportion.put(nineGrid21,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid21).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(midumLevel) && proformanceLevel.equals(midumLevel)){
                proportion.setText(lowerLevel);
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid22)){
                    ablilityProformanceProportion.put(nineGrid22,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid22).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(midumLevel) && proformanceLevel.equals(highLevel)){
                proportion.setText(lowerLevel);
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid23)){
                    ablilityProformanceProportion.put(nineGrid23,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid23).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(highLevel) && proformanceLevel.equals(lowerLevel)){
                proportion.setText(lowerLevel);
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid31)){
                    ablilityProformanceProportion.put(nineGrid31,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid31).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(highLevel) && proformanceLevel.equals(midumLevel)){
                proportion.setText(lowerLevel);
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid32)){
                    ablilityProformanceProportion.put(nineGrid32,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid32).getUserIds().add(userId);
                }
            }

            if(ablilityLevel.equals(highLevel) && proformanceLevel.equals(highLevel)){
                proportion.setText(lowerLevel);
                proportion.getUserIds().add(userId);
                if(!ablilityProformanceProportion.containsKey(nineGrid33)){
                    ablilityProformanceProportion.put(nineGrid33,proportion);
                }else{
                    ablilityProformanceProportion.get(nineGrid33).getUserIds().add(userId);
                }
            }
        }

        Proportion tmp=new Proportion();
        tmp.setValue(BigDecimal.ZERO);
        tmp.setUserIds(new ArrayList<>());
        tmp.setPercentage("0%");
        if(ablilityProformanceProportion.get(nineGrid11)!=null){
            ablilityProformanceProportion.get(nineGrid11).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid11).getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid11).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid11).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid11,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid12)!=null){
            ablilityProformanceProportion.get(nineGrid12).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid12).getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid12).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid12).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid12,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid13)!=null) {
            ablilityProformanceProportion.get(nineGrid13).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid13).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid13).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid13).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid13,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid21)!=null) {
            ablilityProformanceProportion.get(nineGrid21).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid21).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid21).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid21).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid21,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid22)!=null) {
            ablilityProformanceProportion.get(nineGrid22).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid22).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid22).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid22).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid22,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid23)!=null) {
            ablilityProformanceProportion.get(nineGrid23).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid23).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid23).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid23).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid23,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid31)!=null) {
            ablilityProformanceProportion.get(nineGrid31).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid31).getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid31).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid31).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid31,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid32)!=null) {
            ablilityProformanceProportion.get(nineGrid32).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid32).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid32).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid32).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid32,tmp);
        }

        if(ablilityProformanceProportion.get(nineGrid33)!=null) {
            ablilityProformanceProportion.get(nineGrid33).setValue(new BigDecimal(ablilityProformanceProportion.get(nineGrid33).getUserIds().size()).divide(new BigDecimal(userSize), 3, BigDecimal.ROUND_HALF_UP));
            ablilityProformanceProportion.get(nineGrid33).setPercentage(NumberUtil.toPercent(ablilityProformanceProportion.get(nineGrid33).getValue().multiply(new BigDecimal(100)), 1));
        }else{
            ablilityProformanceProportion.put(nineGrid33,tmp);
        }
        return  ablilityProformanceProportion;
    }

    /**
     * 计算心理健康总评占比,非数字计算
     * @param result
     * @param userMenlthStyles
     * @return
     */
    public static  void getTeamMenlthProportion(ReportResult result,Map<Integer, String> userMenlthStyles){
        result.getProportion().put("心理健康总评",culProportionByUserEntry(userMenlthStyles));
    }

    /**
     * 任职风险高风险比例
     * @param result
     * @param indexUserScores
     */
    public  static void getJobRiskProportion(ReportResult result,LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScores){
        String hignRisk="高风险比例";
        String dishignRisk="非高风险比例";
        Map<String, Map<String, Proportion>> indexPoportion=new HashMap<>();
        for(ReportScore indexScore:result.getReportScore()) {
            List<String> targetIndexNames = new ArrayList<>();
            targetIndexNames.add(indexScore.getIndexName());
            List<IntervalDef> intervalDefArrAdvantage = new ArrayList<>();
            IntervalDef intervalDef0 = new IntervalDef();
            intervalDef0.setInterval("(8,10]");
            intervalDef0.setResult(hignRisk);
            intervalDefArrAdvantage.add(intervalDef0);

            IntervalDef intervalDef01 = new IntervalDef();
            intervalDef01.setInterval("[0,8]");
            intervalDef01.setResult(dishignRisk);
            intervalDefArrAdvantage.add(intervalDef01);
            //计算得出
            Map<String, Proportion> map = culProportion(targetIndexNames, indexUserScores, intervalDefArrAdvantage).get(indexScore.getIndexName());

            String percentage = "0";
            Map<String, Proportion> mapr = new HashMap<>();
            Proportion proportion = new Proportion();
            if (map.get(hignRisk) != null) {
                percentage = map.get(hignRisk).getPercentage().replace("%", "");
                proportion.setValue(map.get(hignRisk).getValue());
                proportion.setPercentage(map.get(hignRisk).getPercentage());
                if(map.get(hignRisk).getValue().doubleValue() >= 0.2){
                    proportion.setColorStyle("R");
                }else{
                    proportion.setColorStyle("W");
                }
                proportion.setUserIds(map.get(hignRisk).getUserIds());
            }else{
                proportion.setValue(BigDecimal.ZERO);
                proportion.setPercentage("0%");
            }
            mapr.put(hignRisk, proportion);
            indexPoportion.put(indexScore.getIndexName(),mapr);
        }
        result.setProportion(indexPoportion);
    }

    /**
     * 计算驱动力占比
     * @param result
     * @param userIndexScores
     * @return
     */
    public static void getTeamIDriverProportion(ReportResult result,Map<Integer, LinkedHashMap<String, BigDecimal>> userIndexScores,LinkedHashMap<String,Map<Integer,BigDecimal>> indexUserScores){
        List<IntervalDef> intervalDefArr=new ArrayList<>();

        IntervalDef intervalDef1=new IntervalDef();
        intervalDef1.setInterval("[0,6]");
        intervalDef1.setResult("低");
        intervalDefArr.add(intervalDef1);

        IntervalDef intervalDef2=new IntervalDef();
        intervalDef2.setInterval("(6,10]");
        intervalDef2.setResult("高");
        intervalDefArr.add(intervalDef2);

        LinkedHashMap<String, Map<String, Proportion>> proportionMap = new LinkedHashMap<>();
        List<String> targetIndexNames=new ArrayList<>();
        for(ReportScore indexScore:result.getReportScore()){
            targetIndexNames.add(indexScore.getIndexName());
        }
        proportionMap=culProportion(targetIndexNames,indexUserScores,intervalDefArr);
        Map<String, Proportion> indexProportion=new HashMap<>();
        for(String indexName: proportionMap.keySet()){
            Map<String, Proportion> resultProportion=proportionMap.get(indexName);
            Proportion proportion=resultProportion.get("高");//找大于6分的比例
            BigDecimal percent;
            if(proportion!=null){
                percent=proportion.getValue();

                if(percent.compareTo(BigDecimal.ZERO)>0 ){
                    if(percent.compareTo(new BigDecimal(0.667))<=0){
                        proportion.setColorStyle("T707070");
                        proportion.setText("比例小于等于66.7%");
                    }else{
                        proportion.setColorStyle("T3652b5");
                        proportion.setText("比例大于66.7%");
                    }
                    indexProportion.put(indexName,proportion);
                }
            }
        }
        if(indexProportion.size()<1){
            result.setCommentResult("该团队暂无团队驱动锚。");
        }else{
            Map<String, Map<String, Proportion>> indexProportionMap = new LinkedHashMap<>();
            indexProportionMap.put("团队驱动锚",indexProportion);
            result.setProportion(indexProportionMap);
        }
    }

    /**
     * 计算各个指标用户分布占比
     * @param userIndexScores
     * @return
     */
    public static Map<String, Proportion> culProportionByUserIndex(Map<Integer, LinkedHashMap<String, BigDecimal>> userIndexScores){
        Integer userSize=userIndexScores.size();
        LinkedHashMap<String,Map<Integer,BigDecimal>> indexUserScores=toIndexUserScoreMap(userIndexScores);
        LinkedHashMap<String, Proportion> indexProportion=new LinkedHashMap<>();
        for (String indexName : indexUserScores.keySet()) {
            Map<Integer, BigDecimal> userScoreMap = indexUserScores.get(indexName);
            Proportion proportion=new Proportion();
            proportion.setValue(new BigDecimal(userScoreMap.size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
            proportion.setPercentage(NumberUtil.toPercent(proportion.getValue().multiply(new BigDecimal(100)), 1));

            indexProportion.put(indexName, proportion);
        }

        return indexProportion;
    }

    /**
     * 根据用户词条算占比
     *
     * @return
     */
    public static Map<String, Proportion> culProportionByUserEntry(Map<Integer, String> userEntryMap) {

        Map<String, Proportion> map = new HashMap<>();
        for (Map.Entry<Integer, String> entry : userEntryMap.entrySet()) {
            String result = entry.getValue();
            IntervalDef def=new IntervalDef();
            def.setResult(result);
            setProportionMap(map, def, entry.getKey());
        }

        for (Proportion proportion : map.values()) {
            proportion.setValue(new BigDecimal(proportion.getUserIds().size()).divide(new BigDecimal(userEntryMap.size()),3,BigDecimal.ROUND_HALF_UP));
            proportion.setPercentage(NumberUtil.toPercent(proportion.getValue().multiply(new BigDecimal(100)), 1));
        }

        return map;
    }


    /**
     * 计算商推风格占比,非数字计算
     * @param result
     * @param userBusinessReasonStyles
     * @return
     */
    public static  void getTeamBusinessReasonStyleProportion(ReportResult result,Map<Integer, String> userBusinessReasonStyles){
        result.getProportion().put("商业推理风格",culProportionByUserEntry(userBusinessReasonStyles));
    }

    /**
     * 计算商推、认知占比
     * @param result
     * @param indexUserScores
     * @return
     */
    public static  LinkedHashMap<String, Map<String, Proportion>> getTeamBusinessReasonProportion(ReportResult result,LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScores){
        LinkedHashMap<String, Map<String, Proportion>> proportionMap = new LinkedHashMap<>();
        List<String> targetIndexNames=new ArrayList<>();
        for(ReportScore indexScore:result.getReportScore()){
            targetIndexNames.add(indexScore.getIndexName());
        }
        //红色：低[0,4]
        //蓝色：中(4,6]
        //绿色：高(6,10]

        List<IntervalDef> intervalDefArr=new ArrayList<>();
        IntervalDef intervalDef1=new IntervalDef();
        intervalDef1.setInterval("[0,4]");
        intervalDef1.setResult("低");
        intervalDef1.setColorStyle("R");
        intervalDefArr.add(intervalDef1);

        IntervalDef intervalDef2=new IntervalDef();
        intervalDef2.setInterval("(4,6]");
        intervalDef2.setResult("中");
        intervalDef2.setColorStyle("B");
        intervalDefArr.add(intervalDef2);

        IntervalDef intervalDef3=new IntervalDef();
        intervalDef3.setInterval("(6,10]");
        intervalDef3.setResult("高");
        intervalDef3.setColorStyle("G");
        intervalDefArr.add(intervalDef3);

        proportionMap=culProportion(targetIndexNames,indexUserScores,intervalDefArr);

        return proportionMap;
    }

    /**
     * 计算工作环优势占比(根据优势成员比例出)
     * @param result
     * @param indexUserScores
     * @return
     */
    public static  Map<String, Map<String, Proportion>> getTeamWorkingRingProportion(ReportResult result,LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScores){
        Map<String, Map<String, Proportion>> proportionMap = new HashMap<>();
        String advantage="优势";
        String medium="中等";
        String developed ="待发展";
        String disadvantage="劣势";
        List<IntervalDef> intervalDefArr=new ArrayList<>();
        IntervalDef intervalDef1=new IntervalDef();
        intervalDef1.setInterval("[0,20]");
        intervalDef1.setResult(developed);
        intervalDef1.setColorStyle("pink");
        intervalDefArr.add(intervalDef1);

        IntervalDef intervalDef2=new IntervalDef();
        intervalDef2.setInterval("(20,50)");
        intervalDef2.setResult(medium);
        intervalDef2.setColorStyle("white");
        intervalDefArr.add(intervalDef2);

        IntervalDef intervalDef3=new IntervalDef();
        intervalDef3.setInterval("[50,100]");
        intervalDef3.setResult(advantage);
        intervalDef3.setColorStyle("green");
        intervalDefArr.add(intervalDef3);

        for(ReportScore indexScore:result.getReportScore()){
            List<String> targetIndexNames=new ArrayList<>();
            targetIndexNames.add(indexScore.getIndexName());
            List<IntervalDef> intervalDefArrAdvantage=new ArrayList<>();
            IntervalDef intervalDef0=new IntervalDef();
            intervalDef0.setInterval("(6,10]");
            intervalDef0.setResult(advantage);
            intervalDefArrAdvantage.add(intervalDef0);

            IntervalDef intervalDef01=new IntervalDef();
            intervalDef01.setInterval("[0,6]");
            intervalDef01.setResult(disadvantage);
            intervalDefArrAdvantage.add(intervalDef01);
            //计算得出
            Map<String, Proportion> map=culProportion(targetIndexNames,indexUserScores,intervalDefArrAdvantage).get(indexScore.getIndexName());
            Map<String, Proportion> mapr=new HashMap<>();
            Proportion proportion=new Proportion();
            String percentage = "";

            if (map.get(advantage) != null){
                proportion.setValue(map.get(advantage).getValue());
                proportion.setPercentage(map.get(advantage).getPercentage());
                percentage = map.get(advantage).getPercentage().replace("%","");
            }else{
                percentage="0";
                proportion.setValue(BigDecimal.ZERO);
                proportion.setPercentage("0%");
            }
            String textValue="";
            IntervalDef intervalDefVal=new IntervalDef();

            if (advantage.equals(intervalMatchAndGetResultFunction(intervalDefArr,percentage).getResult())) {
                textValue=advantage;
                intervalDefVal=intervalDef3;
            }else if (medium.equals(intervalMatchAndGetResultFunction(intervalDefArr,percentage).getResult())) {
                textValue=medium;
                intervalDefVal=intervalDef2;
            } else if (developed.equals(intervalMatchAndGetResultFunction(intervalDefArr,percentage).getResult())) {
                textValue=developed;
                intervalDefVal=intervalDef1;
            }
            proportion.setText(textValue);
            proportion.setColorStyle(intervalDefVal.getColorStyle());
            proportion.setInteval(intervalDefVal.getInterval());
            proportion.setUserIds(map.get(advantage)==null?null:map.get(advantage).getUserIds());
            mapr.put(textValue,proportion);
            proportionMap.put(indexScore.getIndexName(),mapr);
        }
        return proportionMap;
    }

    /**
     * 计算大五占比
     * @param result
     * @param indexUserScores
     * @return
     */
    public static  Map<String, Map<String, Proportion>> getTeamBigFiveProportion(ReportResult result,LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScores){
        Map<String, Map<String, Proportion>> proportionMap = new HashMap<>();
        List<String> targetIndexNames=new ArrayList<>();
        for(ReportScore indexScore:result.getReportScore()){
            targetIndexNames.add(indexScore.getIndexName());
        }
        List<IntervalDef> intervalDefArr=new ArrayList<>();
        IntervalDef intervalDef1=new IntervalDef();
        intervalDef1.setInterval("[1,3.5]");
        intervalDef1.setResult("代表得分范围在[1,3.5]的团队成员比例");
        intervalDef1.setColorStyle("B1");
        intervalDefArr.add(intervalDef1);

        IntervalDef intervalDef2=new IntervalDef();
        intervalDef2.setInterval("(3.5,5.5]");
        intervalDef2.setResult("代表得分范围在(3.5,5.5]的团队成员比例");
        intervalDef2.setColorStyle("B2");
        intervalDefArr.add(intervalDef2);

        IntervalDef intervalDef3=new IntervalDef();
        intervalDef3.setInterval("(5.5,7.5]");
        intervalDef3.setResult("代表得分范围在(5.5,7.5]的团队成员比例");
        intervalDef3.setColorStyle("B3");
        intervalDefArr.add(intervalDef3);

        IntervalDef intervalDef4=new IntervalDef();
        intervalDef4.setInterval("(7.5,10]");
        intervalDef4.setResult("代表得分范围在(7.5,10]的团队成员比例");
        intervalDef4.setColorStyle("B4");
        intervalDefArr.add(intervalDef4);

        proportionMap=culProportion(targetIndexNames,indexUserScores,intervalDefArr);

        return proportionMap;
    }


    /**
     * 计算能力倾向占比
     * @param result
     * @param indexUserScores
     * @return
     */
    public static  Map<String, Map<String, Proportion>> getTeamAbilitiyProportion(ReportResult result,LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScores){
        Map<String, Map<String, Proportion>> proportionMap = new HashMap<>();
        List<String> targetIndexNames=new ArrayList<>();
        for(ReportScore indexScore:result.getReportScore()){
            targetIndexNames.add(indexScore.getIndexName());
        }
        List<IntervalDef> intervalDefArr=new ArrayList<>();
        IntervalDef intervalDef1=new IntervalDef();
        intervalDef1.setInterval("[0,4]");
        intervalDef1.setResult("待发展");
        intervalDef1.setColorStyle("R");
        intervalDefArr.add(intervalDef1);

        IntervalDef intervalDef2=new IntervalDef();
        intervalDef2.setInterval("(4,6]");
        intervalDef2.setResult("中等");
        intervalDef2.setColorStyle("W");
        intervalDefArr.add(intervalDef2);

        IntervalDef intervalDef3=new IntervalDef();
        intervalDef3.setInterval("(6,10]");
        intervalDef3.setResult("优势");
        intervalDef3.setColorStyle("G");
        intervalDefArr.add(intervalDef3);
        proportionMap=culProportion(targetIndexNames,indexUserScores,intervalDefArr);

        return proportionMap;
    }
    /**
     * 能力倾向的报告描述
     * @param result
     */
    public static void getTeamAbilityResult(ReportResult result){
        //二级指标得分从大到小排序
        List<ReportScore> sReportScore=new ArrayList<>();
        sReportScore.addAll(result.getReportScore().subList(1,result.getReportScore().size()));

        Collections.sort(sReportScore,new Comparator<ReportScore>() {

            @Override
            public int compare(ReportScore o1, ReportScore o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
                return o2.getAvgScore().compareTo(o1.getAvgScore());
            }
        });

        List<ReportScore> tReportScore=new ArrayList<>();
        tReportScore.add(result.getReportScore().get(0));
        tReportScore.addAll(sReportScore);
        result.setReportScore(tReportScore);

        //评论
        BigDecimal avgScore=result.getReportScore().get(0).getAvgScore();
        String sp="";
        //[0,4]待发展，(4,6]中等，(6,10]优势
        if (avgScore.compareTo(BigDecimal.ZERO)==1 && avgScore.compareTo(BigDecimal.valueOf(4d))==-1){
            sp="待发展";
        }else if (avgScore.compareTo(BigDecimal.valueOf(4))==1 && avgScore.compareTo(BigDecimal.valueOf(6d))<=0){
            sp = "中等";
        }else if (avgScore.compareTo(BigDecimal.valueOf(6))==1 && avgScore.compareTo(BigDecimal.valueOf(10d))<=0){
            sp="优势";
        }
        String ys="";
        String ls="";
        String commnetResult="该团队能力倾向的均分为"+avgScore+"分，处于"+sp+"水平。";//"各项能力中，该团队的相对优势是"+ys+"，相对劣势是"+ls+"。";
        //排序后第一个值和最后一个值是否相等
        BigDecimal max=sReportScore.get(0).getAvgScore();
        BigDecimal min=sReportScore.get(sReportScore.size()-1).getAvgScore();
        if(max.compareTo(min)==0){
            commnetResult+="团队各项能力得分一致，能力发展较均衡。";
        }else{
            for(ReportScore s:sReportScore){
                if(s.getAvgScore().compareTo(max)==0){
                    if(ys.equals("")){
                        ys=sReportScore.get(0).getIndexName();
                    }else{
                        ys+=","+sReportScore.get(0).getIndexName();
                    }

                }
                if(s.getAvgScore().compareTo(min)==0){
                    if(ls.equals("")){
                        ls=sReportScore.get(0).getIndexName();
                    }else{
                        ls+=","+sReportScore.get(0).getIndexName();
                    }
                }
            }
            commnetResult+="各项能力中，该团队的相对优势是"+ys+"，相对劣势是"+ls+"。";
        }

        result.setCommentResult(commnetResult);
    }


    /**
     *  计算平均数
     * @param scoreList
     * @param scale
     * @return
     */
    public static BigDecimal getAverageScoreByList(List<BigDecimal> scoreList, Integer scale){
        //360允许分数问null,所以防止list中有null，这里统一做过滤
        if (CollectionsUtil.isNotEmpty(scoreList)){
            scoreList = scoreList.stream().filter(m -> !StringUtil.isEmpty(m)).collect(Collectors.toList());
        }

        if (CollectionsUtil.isNotEmpty(scoreList)){

            if (scoreList.size() == 1){
                return scoreList.get(0);
            }
            BigDecimal totalScore =BigDecimal.valueOf(scoreList.stream().mapToDouble(BigDecimal::doubleValue).sum());

            if (totalScore.compareTo(BigDecimal.ZERO) == 0){
                return BigDecimal.ZERO.setScale(scale);
            }

            return totalScore.divide(new BigDecimal(scoreList.size()), scale, BigDecimal.ROUND_HALF_UP);
        }

        return null;
    }

    /**
     * 计算分位分数
     * @param scoreList
     * @param pointDivisions
     * @param scale
     * @return
     */
    private static List<BigDecimal> culPointDivision(List<BigDecimal> scoreList, List<BigDecimal> pointDivisions, Integer scale) {

        //pointDivision = Arrays.asList(Point25Division, Point75Division);

        //若只有一个得分或者0个得分，则不用计算直接返回
        if (scoreList.isEmpty() || scoreList.size() == 1) {
            return pointDivisions.stream().map(p -> scoreList.size() == 0 ? BigDecimal.ZERO : scoreList.get(0)).collect(Collectors.toList());
        }
        if (StringUtil.isEmpty(scale)){
            scale = 1;
        }
        List<BigDecimal> scores = new ArrayList<>();

        BigDecimal score = BigDecimal.ZERO;
        for (BigDecimal pointDivision : pointDivisions) {
            score = evaluateDivision(scoreList, pointDivision);
            scores.add(score.setScale(scale, BigDecimal.ROUND_HALF_UP));
        }

        return scores;
    }
    /**
     * 分位值计算
     * @param sorted
     * @param p
     * @return
     */
    public static BigDecimal evaluateDivision(List<BigDecimal> sorted, BigDecimal p) {
        if (p.compareTo(BigDecimal.ZERO) < 0 || p.compareTo(new BigDecimal(100)) > 0 || sorted.size() == 0) {
            return BigDecimal.ZERO;
        }
        //首先从小到大排序
        sorted = sorted.stream().sorted().collect(Collectors.toList());

        Integer n = sorted.size();
        if (n == 1) {
            return sorted.get(0);
        }
        BigDecimal pos = p.multiply(new BigDecimal(n -1)).divide(new BigDecimal(100));
        BigDecimal fpos = pos.setScale(0, BigDecimal.ROUND_FLOOR);
        int intPos = fpos.intValue();
        if (p.compareTo(new BigDecimal(100)) == 0) {
            return Collections.max(sorted);
        }
        if (p.compareTo(BigDecimal.ZERO) == 0) {
            return Collections.min(sorted);
        }
        BigDecimal dif = pos.subtract(fpos);
        BigDecimal lower = sorted.get(intPos);
        BigDecimal upper = sorted.get(intPos + 1);
        return (BigDecimal.ONE.subtract(dif)).multiply(lower).add(dif.multiply(upper));
    }

    /**
     * 根据区间计算占比
     * 指标，<区间评语，占比>
     *
     * @param targetIndexNames
     * @param indexUserScoreMap
     * @param intervalDef
     * @return
     */
    public static LinkedHashMap<String, Map<String, Proportion>> culProportion(List<String> targetIndexNames, LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScoreMap, List<IntervalDef> intervalDef) {

        LinkedHashMap<String, Map<String, Proportion>> proportionMap = new LinkedHashMap<>();

        LinkedHashMap<String, Proportion> map = null;

        for (String indexName : indexUserScoreMap.keySet()) {

            //若不包含这个指标id，跳过
            if (targetIndexNames != null && !targetIndexNames.contains(indexName)) {
                continue;
            }
            map = new LinkedHashMap<>();

            Map<Integer, BigDecimal> userScoreMap = indexUserScoreMap.get(indexName);
            for (Map.Entry<Integer, BigDecimal> entry : userScoreMap.entrySet()) {
                IntervalDef def=intervalMatchAndGetResultFunction(intervalDef, entry.getValue() + "");
                String result = def.getResult();
                String colorStyle=def.getColorStyle();
                setProportionMap(map, def, entry.getKey());
            }

            int userSize=userScoreMap.size();
            for (Proportion proportion : map.values()) {
                proportion.setValue(new BigDecimal(proportion.getUserIds().size()).divide(new BigDecimal(userSize),3,BigDecimal.ROUND_HALF_UP));
                proportion.setPercentage(NumberUtil.toPercent(proportion.getValue().multiply(new BigDecimal(100)), 1));
            }
            proportionMap.put(indexName, map);
        }

        return proportionMap;
    }

    /**
     * 传入 userId-indexId-score的map，输出indexId-userId-score的map
     * 转置用户、指标的层级结构
     * @return
     */
    public static LinkedHashMap<String, Map<Integer, BigDecimal>> toIndexUserScoreMap(Map<Integer, LinkedHashMap<String,BigDecimal>> userIndexScoreMap){
        LinkedHashMap<String, Map<Integer, BigDecimal>> indexUserScoreMap = new LinkedHashMap<>();

        Map<String,BigDecimal> userScoreMap = null;
        for(Integer id : userIndexScoreMap.keySet()) {

            userScoreMap = userIndexScoreMap.get(id);
            for (String indexName : userScoreMap.keySet()){

                if(!indexUserScoreMap.containsKey(indexName)){
                    indexUserScoreMap.put(indexName, new LinkedHashMap<>());
                }

                indexUserScoreMap.get(indexName).put(id, userScoreMap.get(indexName));
            }
        }

        return indexUserScoreMap;
    }

    /**
     * 加人到对应的评语
     * @param map
     * @param intervalDef
     * @param userId
     * @return
     */
    private static Map<String, Proportion> setProportionMap(Map<String, Proportion> map,IntervalDef intervalDef, Integer userId) {
        if (!map.containsKey(intervalDef.getResult())) {
            Proportion proportion = new Proportion();
            proportion.setText(intervalDef.getResult());
            proportion.setColorStyle(intervalDef.getColorStyle());
            proportion.setUserIds(new ArrayList<>());
            proportion.setInteval(intervalDef.getInterval());
            map.put(intervalDef.getResult(), proportion);
        }
        map.get(intervalDef.getResult()).getUserIds().add(userId);
        return map;
    }

    /**
     * 判断区间并返回匹配区间
     *
     * @param list
     * @param score
     * @return
     */
    public static IntervalDef intervalMatchAndGetResultFunction(List<IntervalDef> list, String score) {
        if (list != null && !StringUtils.isEmpty(score)) {
            for (IntervalDef intervalDef : list) {
                if (intervalMatch(intervalDef.getInterval(), score)) {
                    return intervalDef;
                }
            }
        }
        return new IntervalDef();
    }

    /**
     * 判断分数是否在此区间
     * @param interval
     * @param score
     * @return
     */
    public static boolean intervalMatch(String interval, String score) {
        boolean intervalFlag = false;
        String[] attrTemp = interval.split(",");
        String begin = attrTemp[0].substring(1, attrTemp[0].length());
        String end = attrTemp[1].substring(0, attrTemp[1].length()-1);
        //区间为()类型
        if (interval.startsWith(leftThan) && interval.endsWith(rightThan)) {
            if (intervalJudgment(new BigDecimal(begin), new BigDecimal(end), new BigDecimal(score))) {
                intervalFlag = true;
            }

        } else if (interval.startsWith(leftEqual) && interval.endsWith(rightThan)) {
            if (intervalJudgment(new BigDecimal(Double.valueOf(begin) - accuracy), new BigDecimal(end), new BigDecimal(score))) {
                intervalFlag = true;
            }

        } else if (interval.startsWith(leftThan) && interval.endsWith(rightEqual)) {
            end = attrTemp[1].substring(0, attrTemp[1].indexOf(rightEqual));
            if (intervalJudgment(new BigDecimal(begin), new BigDecimal(Double.valueOf(end) + accuracy), new BigDecimal(score))) {
                intervalFlag = true;
            }

        } else if (interval.startsWith(leftEqual) && interval.endsWith(rightEqual)) {
            end = attrTemp[1].substring(0, attrTemp[1].indexOf(rightEqual));
            if (intervalJudgment(new BigDecimal(Double.valueOf(begin) - accuracy), new BigDecimal(Double.valueOf(end) + accuracy), new BigDecimal(score))) {
                intervalFlag = true;
            }
        }
        return intervalFlag;
    }

    /**
     * 区间判断
     * @param begin
     * @param end
     * @param score
     * @return
     */
    public static boolean intervalJudgment(BigDecimal begin, BigDecimal end, BigDecimal score) {
        if (score.compareTo(begin) == 1 && score.compareTo(end) == -1) {
            return true;
        }
        return false;
    }

}
