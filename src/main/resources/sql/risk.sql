DROP TABLE IF EXISTS `ba_comp`;
create table ba_comp(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	name varchar(255) COMMENT '公司名称',
	comp_type varchar(255) COMMENT '公司类型名称',
	product varchar(255) COMMENT '产品',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
   PRIMARY KEY (`id`)
) comment '公司基本信息';

DROP TABLE IF EXISTS `ba_comp_info`;
create table ba_comp_info(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	comp_id int(10) COMMENT '公司ID',
	info_key varchar(255) NOT NULL COMMENT '名称',
	info_value varchar(255) NOT NULL COMMENT '值',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
   PRIMARY KEY (`id`)
) comment '公司其它信息';

DROP TABLE IF EXISTS `ba_comp_chan_def`;
create table ba_comp_chan_def(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	chan_id int(10) NOT NULL COMMENT '节点ID',
	type varchar(255) NOT NULL COMMENT '行业类型名称',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
  PRIMARY KEY (`id`)
) comment '行业类型默认节点';

DROP TABLE IF EXISTS `ba_comp_chan_auto`;
create table ba_comp_chan_auto(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	comp_id int(10) NOT NULL COMMENT '公司ID',
	chan_id int(10) NOT NULL COMMENT '节点ID',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
  PRIMARY KEY (`id`)
) comment '公司与节点关联';

DROP TABLE IF EXISTS `ba_compchan_data`;
create table ba_compchan_data(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	comp_chan_id int(10) NOT NULL COMMENT '公司节点信息ID',
	comtent text COMMENT '描述',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
  PRIMARY KEY (`id`)
) comment '公司与节点文本数据';

DROP TABLE IF EXISTS `ba_compchan_file`;
create table ba_compchan_file(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	comp_chan_id int(10) NOT NULL COMMENT '公司节点信息ID',
	file_name varchar(255) comment '文件名称', 
	file_url  varchar(255) comment '文件链接', 
	file_size varchar(255) default '' comment '文件大小',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
  PRIMARY KEY (`id`)
) comment '公司与节点附件';

DROP TABLE IF EXISTS `ba_compchan_info`;
create table ba_compchan_info(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	comp_chan_id int(10) NOT NULL COMMENT '公司节点信息ID',
	info_key varchar(255) NOT NULL COMMENT '名称',
	info_value varchar(255) NOT NULL COMMENT '值',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
   PRIMARY KEY (`id`)
) comment '公司与节点其它信息';

create table ba_compchan_score(
	id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
	comp_chan_id int(10) NOT NULL COMMENT '公司节点信息ID',
	score int(10) DEFAULT 0 COMMENT '节点数据评分',
	node_name varchar(255) COMMENT '初审、复审、终审',
  `createDate` datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `createBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updateDate` datetime NOT NULL DEFAULT NOW() COMMENT '更新时间',
  `updateBy` varchar(255) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleFlag` varchar(10) NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
   PRIMARY KEY (`id`)
)comment '公司节点信息评分';

DROP TABLE IF EXISTS `ba_channel`;
CREATE TABLE `ba_channel` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '类型',
  `code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '节点代码',
  `channelName` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '栏目名称',
  `parentId` int(10) DEFAULT '0' COMMENT '父级id',
  `description` varchar(500) CHARACTER SET utf8 DEFAULT '0' COMMENT '栏目描述',
  `url` varchar(200) CHARACTER SET utf8 DEFAULT '0' COMMENT '节点连接',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `createBy` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '创建人',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `updateBy` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '更新人',
  `grade` int(10) DEFAULT NULL COMMENT '级别',
  `deleFlag` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT 'N' COMMENT '是否删除标示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1 COMMENT='栏目表';

INSERT INTO `ba_channel` VALUES ('10', 'book', 'FXKZ17041900001', '风控了', '0', '123', '123', '2017-04-18 16:32:28', '0', '2017-04-18 16:35:50', '0', null, 'Y');
INSERT INTO `ba_channel` VALUES ('11', 'book', 'FXKZ17041900001', '123123', '10', '111', '111', '2017-04-18 16:32:41', '0', '2017-04-18 16:33:05', '0', null, 'Y');
INSERT INTO `ba_channel` VALUES ('12', 'book', 'FXKZ17041900001', '12312', '10', '111', '111', '2017-04-18 16:34:34', '0', '2017-04-18 16:53:58', '0', null, 'Y');
INSERT INTO `ba_channel` VALUES ('13', 'book', 'FXKZ17041900001', '2222222222222', '10', '222', '222', '2017-04-18 16:35:11', '0', '2017-04-18 16:35:41', '0', null, 'Y');
INSERT INTO `ba_channel` VALUES ('14', 'book', 'FXKZ17041900001', '123', '12', '123', '123', '2017-04-18 16:49:07', '0', '2017-04-18 16:53:53', '0', null, 'Y');
INSERT INTO `ba_channel` VALUES ('15', 'book', 'FXKZ17041900001', '宏观环境', '0', '宏观环境', '/111', '2017-04-18 17:21:56', '0', '2017-04-18 17:21:56', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('16', 'book', 'FXKZ17041900001', '主体信用', '0', '主体信用', '/222', '2017-04-18 17:22:32', '0', '2017-04-18 17:22:32', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('17', 'book', 'FXKZ17041900001', '交易能力', '0', '交易能力', '/333', '2017-04-18 17:22:51', '0', '2017-04-18 17:22:51', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('18', 'book', 'FXKZ17041900001', '结构安排', '0', '结构安排', '/444', '2017-04-18 17:23:13', '0', '2017-04-18 17:23:13', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('19', 'book', 'FXKZ17041900001', '合作机构', '0', '合作机构', '/555', '2017-04-18 17:23:35', '0', '2017-04-18 17:23:35', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('20', 'book', 'FXKZ17041900001', '市场环境判断', '15', '市场环境判断', '11', '2017-04-18 17:24:10', '0', '2017-04-18 17:24:10', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('21', 'book', 'FXKZ17041900001', '宏观经济', '20', '宏观经济', '111', '2017-04-18 17:24:24', '0', '2017-04-18 17:24:24', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('22', 'book', 'FXKZ17041900001', '金融形势', '20', '金融形势', '1', '2017-04-18 17:24:37', '0', '2017-04-18 17:24:37', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('23', 'book', 'FXKZ17041900001', '行业周期', '20', '行业周期', '1', '2017-04-18 17:24:55', '0', '2017-04-18 17:24:55', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('24', 'book', 'FXKZ17041900001', '企业实际控制人的诚信记录', '16', '企业实际控制人的诚信记录', '2', '2017-04-18 17:25:24', '0', '2017-04-18 17:25:24', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('25', 'book', 'FXKZ17041900001', '个人保证资料', '24', '个人保证资料', '1', '2017-04-18 17:25:51', '0', '2017-04-18 17:25:51', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('26', 'book', 'FXKZ17041900001', '户籍及身份证明', '25', '户籍及身份证明', '2', '2017-04-18 17:26:09', '0', '2017-04-18 17:26:09', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('27', 'book', 'FXKZ17041900001', '结婚证或婚姻状况证明', '25', '结婚证或婚姻状况证明', '1', '2017-04-18 17:26:24', '0', '2017-04-18 17:26:24', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('28', 'book', 'FXKZ17041900001', '主要财产清单', '25', '主要财产清单', '1', '2017-04-18 17:26:41', '0', '2017-04-18 17:26:41', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('29', 'book', 'FXKZ17041900001', '主要资产的权属证明或购置凭证', '25', '主要资产的权属证明或购置凭证', '1', '2017-04-18 17:26:54', '0', '2017-04-18 17:26:54', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('30', 'book', 'FXKZ17041900001', '个人征信报告', '25', '个人征信报告', '个人征信报告', '2017-04-18 17:27:17', '0', '2017-04-18 17:27:17', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('31', 'book', 'FXKZ17041900001', '客户基础资料', '24', '客户基础资料', '客户基础资料', '2017-04-18 17:27:38', '0', '2017-04-18 17:27:38', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('32', 'book', 'FXKZ17041900001', '营业执照', '31', '营业执照', '营业执照', '2017-04-18 17:27:51', '0', '2017-04-18 17:27:51', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('33', 'book', 'FXKZ17041900001', '工商查册资料', '31', '工商查册资料', '工商查册资料', '2017-04-18 17:28:03', '0', '2017-04-18 17:28:03', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('34', 'book', 'FXKZ17041900001', '法人代码证书税务登记证', '31', '法人代码证书税务登记证', '法人代码证书税务登记证', '2017-04-18 17:28:27', '0', '2017-04-18 17:28:27', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('35', 'book', 'FXKZ17041900001', '公司章程', '31', '公司章程', '公司章程', '2017-04-18 17:28:40', '0', '2017-04-18 17:28:40', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('36', 'book', 'FXKZ17041900001', '验资报告', '31', '验资报告', '验资报告', '2017-04-18 17:28:55', '0', '2017-04-18 17:28:55', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('37', 'book', 'FXKZ17041900001', '法人代表身份证明', '31', '法人代表身份证明', '法人代表身份证明', '2017-04-18 17:29:09', '0', '2017-04-18 17:29:09', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('38', 'book', 'FXKZ17041900001', '贷款卡及贷款卡查询记录', '31', '贷款卡及贷款卡查询记录', '贷款卡及贷款卡查询记录', '2017-04-18 17:29:25', '0', '2017-04-18 17:29:25', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('39', 'book', 'FXKZ17041900001', '三年及最近一期的财务报表', '31', '三年及最近一期的财务报表', '三年及最近一期的财务报表', '2017-04-18 17:29:42', '0', '2017-04-18 17:29:42', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('40', 'book', 'FXKZ17041900001', '最近6个月水电费单据', '31', '最近6个月水电费单据', '最近6个月水电费单据', '2017-04-18 17:29:56', '0', '2017-04-18 17:29:56', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('41', 'book', 'FXKZ17041900001', '最近6个月银行对账单', '31', '最近6个月银行对账单', '最近6个月银行对账单', '2017-04-18 17:30:10', '0', '2017-04-18 17:30:10', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('42', 'book', 'FXKZ17041900001', '申请人主要财产清单', '31', '申请人主要财产清单', '申请人主要财产清单', '2017-04-18 17:30:24', '0', '2017-04-18 17:30:24', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('43', 'book', 'FXKZ17041900001', '新华信公共记录报告', '31', '新华信公共记录报告', '新华信公共记录报告', '2017-04-18 17:30:36', '0', '2017-04-18 17:30:36', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('44', 'book', 'FXKZ17041900001', '海关或外管局等第三方机构权威资料', '31', '海关或外管局等第三方机构权威资料', '海关或外管局等第三方机构权威资料', '2017-04-18 17:30:52', '0', '2017-04-18 17:30:52', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('45', 'book', 'FXKZ17041900001', '其他', '31', '其他', '其他', '2017-04-18 17:31:08', '0', '2017-04-18 17:31:08', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('46', 'book', 'FXKZ17041900001', '准入标准', '24', '准入标准', '准入标准', '2017-04-18 17:31:35', '0', '2017-04-18 17:33:02', '0', null, 'Y');
INSERT INTO `ba_channel` VALUES ('47', 'book', 'FXKZ17041900001', '可能累及企业的重大不利事项', '16', '可能累及企业的重大不利事项', '可能累及企业的重大不利事项', '2017-04-18 17:33:13', '0', '2017-04-18 17:33:13', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('48', 'book', 'FXKZ17041900001', '真实交易背景，注重交易的连续性与稳定性', '17', '真实交易背景，注重交易的连续性与稳定性', '真实交易背景，注重交易的连续性与稳定性', '2017-04-18 17:33:41', '0', '2017-04-18 17:33:41', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('49', 'book', 'FXKZ17041900001', '依托核心企业', '17', '依托核心企业', '依托核心企业', '2017-04-18 17:33:57', '0', '2017-04-18 17:33:57', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('50', 'book', 'FXKZ17041900001', '物流', '18', '物流', '物流', '2017-04-18 17:34:15', '0', '2017-04-18 17:34:15', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('51', 'book', 'FXKZ17041900001', '商品目录管理制度', '50', '商品目录管理制度', '商品目录管理制度', '2017-04-18 17:34:36', '0', '2017-04-18 17:34:36', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('52', 'book', 'FXKZ17041900001', '逐日盯市制度', '50', '逐日盯市制度', '逐日盯市制度', '2017-04-18 17:34:50', '0', '2017-04-18 17:34:50', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('53', 'book', 'FXKZ17041900001', '跌价补偿制度', '50', '跌价补偿制度', '跌价补偿制度', '2017-04-18 17:35:03', '0', '2017-04-18 17:35:03', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('54', 'book', 'FXKZ17041900001', '巡核库制度', '50', '巡核库制度', '巡核库制度', '2017-04-18 17:35:17', '0', '2017-04-18 17:35:17', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('55', 'book', 'FXKZ17041900001', '资金流', '18', '资金流', '资金流', '2017-04-18 17:35:39', '0', '2017-04-18 17:35:39', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('56', 'book', 'FXKZ17041900001', '资金流循环所匹配的风险控制方案', '55', '资金流循环所匹配的风险控制方案', '资金流循环所匹配的风险控制方案', '2017-04-18 17:35:53', '0', '2017-04-18 17:35:53', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('57', 'book', 'FXKZ17041900001', '锁定资金回款路径控制', '55', '锁定资金回款路径控制', '锁定资金回款路径控制', '2017-04-18 17:36:08', '0', '2017-04-18 17:36:08', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('58', 'book', 'FXKZ17041900001', '信息流', '18', '信息流', '信息流', '2017-04-18 17:36:27', '0', '2017-04-18 17:36:27', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('59', 'book', 'FXKZ17041900001', '企业与云化网', '58', '企业与云化网', '企业与云化网', '2017-04-18 17:36:56', '0', '2017-04-18 17:36:56', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('60', 'book', 'FXKZ17041900001', '国华与云化网', '58', '国华与云化网', '国华与云化网', '2017-04-18 17:37:16', '0', '2017-04-18 17:37:16', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('61', 'book', 'FXKZ17041900001', '企业在云化网注册，合约、审批通过', '59', '企业在云化网注册，合约、审批通过', '企业在云化网注册，合约、审批通过', '2017-04-18 17:37:41', '0', '2017-04-18 17:37:41', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('62', 'book', 'FXKZ17041900001', '企业采购申请', '59', '企业采购申请', '企业采购申请', '2017-04-18 17:37:54', '0', '2017-04-18 17:37:54', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('63', 'book', 'FXKZ17041900001', '采购交易确认', '59', '采购交易确认', '采购交易确认', '2017-04-18 17:38:06', '0', '2017-04-18 17:38:06', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('64', 'book', 'FXKZ17041900001', '合作协议，信息真实性保障', '60', '合作协议，信息真实性保障', '合作协议，信息真实性保障', '2017-04-18 17:38:23', '0', '2017-04-18 17:38:23', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('65', 'book', 'FXKZ17041900001', '国华采购清单', '60', '国华采购清单', '国华采购清单', '2017-04-18 17:38:44', '0', '2017-04-18 17:38:44', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('66', 'book', 'FXKZ17041900001', '物流公司', '19', '物流公司', '物流公司', '2017-04-18 17:39:00', '0', '2017-04-18 17:39:00', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('67', 'book', 'FXKZ17041900001', '合格监管管方', '19', '合格监管管方', '合格监管管方', '2017-04-18 17:39:16', '0', '2017-04-18 17:39:16', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('68', 'book', 'FXKZ17041900001', '保险公司', '19', '保险公司', '保险公司', '2017-04-18 17:39:29', '0', '2017-04-18 17:39:29', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('69', 'book', 'FXKZ17041900001', '政府机构', '19', '政府机构', '政府机构', '2017-04-18 17:39:42', '0', '2017-04-18 17:39:42', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('70', 'book', 'FXKZ17041900001', '外贸代理公司', '19', '外贸代理公司', '外贸代理公司', '2017-04-18 17:39:58', '0', '2017-04-18 17:39:58', '0', null, 'N');
INSERT INTO `ba_channel` VALUES ('71', 'book', 'FXKZ17041900001', '保理商', '19', '保理商', '保理商', '2017-04-18 17:40:17', '0', '2017-04-18 17:40:17', '0', null, 'N');

