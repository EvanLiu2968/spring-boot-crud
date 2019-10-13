
INSERT INTO `user_group`(id,group_name,code,note) VALUES (
  '1',
  '管理员',
  'admin',
  '管理员分组'
);
INSERT INTO `user_group`(id,group_name,code,note) VALUES (
  '2',
  '普通用户',
  'normal',
  '普通用户分组'
);

INSERT INTO `user`(id,name,phone,email,url,password,group_id)  VALUES (
  '1',
  '管理员',
  'admin',
  'evanliu2968@gmail.com',
  '123456',
  1
);
