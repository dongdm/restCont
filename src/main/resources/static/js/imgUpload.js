//图片裁减
$('.photo-crop').click(function () {
    $(".uploadphoto-popup").removeClass('s05x');
    $(".mfp-bg").show();
    if($("input[name='img_type']")){
        var img_type = $(this).attr("data-img-type");
        $("input[name='img_type']").val(img_type);
    }
    clearCutImg();
});

//上传图片到服务器（flag表示是否直接上传到oss，1表示是，其余为否）
function img_upload(flag){
    clearCutImg();
    var fileElementId = "image_file";
    ajaxFileUpload(fileElementId,"img_upload_bk",flag);
}


//上传图片完成后的回显函数(仅仅上传头像到服务器，实现头像剪切)
var imgAreaSelectApi;
var pai = "";
function img_upload_bk(file){
    //alert("上传图像width:" + file.imgWidth + "height:" + file.imgHeight);
    img = '<img src=' + file.filePath + '/>';
    var obj = $(".pup-photo");
    obj.html(img);

    //获得上传图像类型
    var imgType = $("input[name='img_type']").val();
    var width = 0,height = 0;
    if("cod_business_pic_logo" == imgType){
        //项目LOGO
        width = 230;
        height = 175;
    }else if("img_big_business" == imgType){
        //项目大图1420 x 566
        width = 1420;
        height = 566;
    }else if("img_intro_business"){
        //档次说明
        width = 230;
        height = 175;
    }else if("img_intro" == imgType){
        //用户头像
        width = 128;
        height = 128;
    }else if("frontCard" == imgType ||
        "negativeCard" == imgType ||
        "personOtherProve" == imgType ||
        "cod_org_no_img" == imgType ||
        "corpOtherProve" == imgType ||
        "cod_cust_lincense_img" == imgType){
        //个人实名制认证（证件正面、证件反面、其他证件）
        width = 320;
        height = 200;
    }else if("equit_add01_logoImg2"==imgType){
        width = 1420;
        height = 566;
    }
    var imgHeight = file.imgHeight;
    var imgWidth = file.imgWidth;
    //计算选择区域大小
    if(imgHeight < height){
        height = file.imgHeight;
        $("input[name='height']").val(height);
        $("#imgUploadError").html("上传图片长宽不合适").show();
    }
    if(imgWidth < width){
        width = file.imgWidth;
        $("input[name='width']").val(width);
        $("#imgUploadError").html("上传图片长宽不合适").show();
    }else{
        $("input[name='height']").val(height);
        $("input[name='width']").val(width);
        if(file.imgWidth > 600){
            pai = (600 / file.imgWidth);
            width = pai * width;
            height = pai * height;
        }
    }
    //alert("可选区域width:" + width + "height:" + height);
    $("input[name='srcImgPath']").val(file.filePath);
    $("input[name='x1']").val(0);
    $("input[name='y1']").val(0);

    //当上传图像为个人证件正面、反面、其他证件时使用另一种裁剪方式
    if("frontCard" == imgType ||"negativeCard" == imgType || "personOtherProve" == imgType ||
        "cod_org_no_img" == imgType || "corpOtherProve" == imgType ||
        "cod_cust_lincense_img" == imgType ){
        imgAreaSelectApi = $('.pup-photo img').imgAreaSelect({
            x1: 0, y1: 0, x2: width, y2: height,
            handles : true,// true，调整手柄则会显示在选择区域内
            show : true,    // 选区会显示
            persistent : true,  // true，选区以外点击不会启用一个新选区（只能移动/调整现有选区）
            resizable : true,   // true， 选区面积可调整大小
            fadeSpeed : 200,
            instance : true,
            minWidth : Math.floor(320/4),// 选取的最小宽度
            minHeight : Math.floor(200/4),  // 选取的最小高度
            aspectRatio : '320:200', // 选区的显示比率 400:300
            onSelectChange : preview
        });
    }else if("cod_business_pic_logo" == imgType ||
        "img_intro_business" == imgType){
        //上传项目封面、档次图片时进行另外一种裁剪方式
        imgAreaSelectApi = $('.pup-photo img').imgAreaSelect({
            x1: 0, y1: 0, x2: width, y2: height,
            handles : true,// true，调整手柄则会显示在选择区域内
            show : true,    // 选区会显示
            persistent : true,  // true，选区以外点击不会启用一个新选区（只能移动/调整现有选区）
            resizable : true,   // true， 选区面积可调整大小
            fadeSpeed : 200,
            instance : true,
            minWidth : Math.floor(230),// 选取的最小宽度
            minHeight : Math.floor(175),  // 选取的最小高度
            aspectRatio : '230:175', // 选区的显示比率 400:300
            onSelectChange : preview
        });
    }else if("equit_add01_logoImg2"==imgType){
        //上传项目封面、档次图片时进行另外一种裁剪方式
        imgAreaSelectApi = $('.pup-photo img').imgAreaSelect({
            x1: 0, y1: 0, x2: width, y2: height,
            handles : true,// true，调整手柄则会显示在选择区域内
            show : true,    // 选区会显示
            persistent : true,  // true，选区以外点击不会启用一个新选区（只能移动/调整现有选区）
            resizable : true,   // true， 选区面积可调整大小
            fadeSpeed : 200,
            instance : true,
            minWidth : Math.floor(533),// 选取的最小宽度
            minHeight : Math.floor(135),  // 选取的最小高度
            aspectRatio : '533:135', // 选区的显示比率 400:300
            onSelectChange : preview
        });
    }else{
        console.info("头像上传" + width + height);
        imgAreaSelectApi = $('.pup-photo img').imgAreaSelect({
            x1: 0, y1: 0, x2: width, y2: height,
            handles : true,// true，调整手柄则会显示在选择区域内
            show : true,    // 选区会显示
            persistent : true,  // true，选区以外点击不会启用一个新选区（只能移动/调整现有选区）
            resizable : false,   // true， 选区面积可调整大小
            fadeSpeed : 200,
            instance : true,
            onSelectChange : preview
        });
    }
}

//剪切图片
function preview(img, selection) {
    if (!selection.width || !selection.height){
        return;
    }
    /*
     var scaleX = 100 / selection.width;
     var scaleY = 100 / selection.height;
     $('.user-avatar1 img').css({
     width : Math.round(scaleX * 300),
     height : Math.round(scaleY * 300),
     marginLeft : -Math.round(scaleX * selection.x1),
     marginTop : -Math.round(scaleY * selection.y1)
     });
     */
    if(pai){
        var x1 = selection.x1 / pai;
        x1 = parseInt(x1);
        var y1 = selection.y1 / pai;
        y1 = parseInt(y1);
        $("input[name='x1']").val(x1);
        $("input[name='y1']").val(y1);
    }else{
        var x1 = parseInt(selection.x1);
        var y1 = parseInt(selection.y1);
        $("input[name='x1']").val(x1);
        $("input[name='y1']").val(y1);
    }
    var imgType = $("input[name='img_type']").val();
    if("frontCard" == imgType ||"negativeCard" == imgType || "personOtherProve" == imgType ||
        "cod_org_no_img" == imgType || "corpOtherProve" == imgType ||
        "cod_cust_lincense_img" == imgType ||
        "cod_business_pic_logo" == imgType ||
        "img_intro_business" == imgType||"equit_add01_logoImg2"==imgType){
        if(pai){
            var height = selection.height / pai;
            height = parseInt(height);
            var width = selection.width / pai;
            width = parseInt(width);
            $("input[name='height']").val(height);
            $("input[name='width']").val(width);
        }else{
            $("input[name='height']").val(selection.height);
            $("input[name='width']").val(selection.width);
        }
    }
}



//清空图像裁剪信息
function clearCutImg(){
    pai = "";
    $("input[name='srcImgPath']").val("");
    $("input[name='x1']").val("");
    $("input[name='y1']").val("");
    $("input[name='width']").val("");
    $("input[name='height']").val("");
    $("#imgUploadError").html("").hide();
    //消除裁剪区域
    if(imgAreaSelectApi){
        imgAreaSelectApi.setSelection(0, 0, 0, 0);
        imgAreaSelectApi.update();
        $(".pup-photo img").remove();
        $(".pup-photo").html("");
        $(".imgareaselect-outer").hide();
    }
}

//上传文件
function ajaxFileUpload(fileElementId,type,param,fileType)
{
//	 		$("#loading").ajaxStart(function(){
//	 			$(this).show();
//	 		}).ajaxComplete(function(){
//	 			$(this).hide();
//	 		});
    //判断图片是否上传到OSS,默认上传
    if(!param){
        param = 1;
    }
    //文件类型
    if(!fileType){
        fileType = "image";
    }
    $.ajaxFileUpload
    (
        {
            url:'/uploadFiles',
            secureuri:false,
            fileElementId:fileElementId,
            dataType: 'text',
            data:{"flag":param,"type":fileType},
            success: function (data, status)
            {
                data = data.replace("<pre>","");
                data = data.replace("</pre>","");
                var msg = eval('(' + data + ')');
                var replyCode = msg.trans.replyCode;
                if (replyCode == "0000") {
                    //文件上传成功
                    var files = msg.data;
                    var file = files[0];
                    if(type == "img_upload_bk"){
                        img_upload_bk(file);
                    }else if(type == "file_upload_bk"){
                        file_upload_bk(file,fileElementId);
                    }

                } else {
                    //showErrorTip(data);
                    if($("#imgUploadError")){
                        $("#imgUploadError").html(msg.trans.replyText).show();
                    }
                }
            },
            error: function (data, status, e)
            {
                //showErrorMes();
            }
        }
    )

    return false;
}

//上传图片的剪切 位置，后台进行剪切
//提交剪切数据
var arrayObj = new Array();
var addProImg = [];
var album=[];
var Video=[];
function submitCutImg(){
    /*
    var flag = doValidate("#cutForm");
    if(!flag){
        $("#imgUploadError").show();
    }
    */
    var data = $("#cutForm").serializeObject();
    //alert("x:" + $("input[name='x1']").val() + "\n\n" + "y:" + $("input[name='y1']").val() + "\n\n" + "width:" + $("input[name='width']").val() + "\n\n" + "height:" + $("input[name='height']").val() + "\n\n");
    //判断图像上传还是修改头像
    var imgType = $("input[name='img_type']").val();
    var bid_type=$("#bid_type").val();

    var int_business=$("#int_business").val();
    var url = '/imgCutUpload';
    /*
    if("img_intro" == imgType){
        //修改头像和图像上传
        url = '/modUserImg';
    }
    */
    //清除可选区域
    clearCutImg();
    $.ajax({
        url : url,
        data : data,
        type : 'POST',
        dataType : 'text',
        success : function(data){
            var msg = eval('(' + data + ')');
            var replyCode = msg.trans.replyCode;
            if (replyCode == "0000") {
                var file = msg.data;
                //获得上传图像类型
                if("cod_business_pic_logo" == imgType){
                    //项目LOGO
                    var obj = $(".ipt-img-1");
                    img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    if(bid_type!=null&&bid_type!=""){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用
                            deleteFile(imgType,"0");
                            addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"business","businessLogo","","oss","n","y",file.filePath);
                        }
                    }
                    $("#cod_business_pic_logo").val(file.filePath);
                    $("#logo_md5").val(file.fileMd5);
                    $("#logo_fileName").val(file.fileName);
                    obj.html(img);
                }else if("img_big_business" == imgType){
                    var imglist = new Array();
                    //项目大图1420 x 566
                    var obj = $(".ipt-img-2");
                    img = '<img src="' + file.filePath + '" width="1420" height="566"/>';
                    if(bid_type!=null){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用
                            deleteFile(imgType);
                            addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"business","contentPic","","oss","y","n",file.filePath);
                        }
                    }

                    $("#img_big_business").val(file.filePath);
                    $("#img_big_md5").val(file.fileMd5);
                    $("#img_big_fileName").val(file.fileName);
                    obj.html(img);
                }else if("img_intro_business" == imgType){

                    var imglist = new Array();
                    //档次图片添加
                    var obj = $("#gradePicture");
                    var img = '<li id="'+file.fileMd5+'"><img src="' + file.filePath + '"/><span class="pos-opt"><a href="javascript:;"   onclick="deleteFile(\''+file.fileMd5+'\',\'1\')"><img src="${Presource}/images/icon-delete.png"  alt=""/></a></span></li>';
                    $("#cod_ext_url").val(file.filePath);
                    var cod_business_amount_lv=$("#cod_business_amount_lv").val();
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"amount","amountImg","","oss","y","n",file.filePath);
                    imglist.push(file.filePath);
                    imglist.push(file.fileMd5);
                    imglist.push(file.fileName);
                    arrayObj.push(imglist+"]]")
                    $("#etag").val(file.fileMd5);
                    $("#fileName").val(file.fileName);
                    $(".ipt-gray").hide();
                    obj.append(img);
                }else if("img_intro" == imgType){
                    //用户头像
                    $("#errortop").html("头像上传成功");
                    var img = '<img src="' + file.filePath + '" width="128" height="128"/>';
                    $(".head-portrait").html(img);
                    $(".portrait > img").attr("src",file.filePath);
                    $(".user-avatar img").attr("src",file.filePath);
                }else if("frontCard" == imgType){
                    //个人证件正面
                    $("#errortop").html("证件正面上传成功");
                    var img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    $("#img_frontCard").html(img);
                    //上传图像信息
                    $("#frontCard").val(file.filePath);
                    $("#frontCard_md5").val(file.fileMd5);
                    $("#frontCard_fileName").val(file.fileName);
                }else if("negativeCard" == imgType){
                    //个人证件反面
                    $("#errortop").html("证件反面上传成功");
                    var img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    $("#img_negativeCard").html(img);
                    //上传图像信息
                    $("#negativeCard").val(file.filePath);
                    $("#negativeCard_md5").val(file.fileMd5);
                    $("#negativeCard_fileName").val(file.fileName);
                }else if("personOtherProve" == imgType){
                    //个人其他证件
                    $("#errortop").html("证件上传成功");
                    var img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    $("#img_personOtherProve").html(img);
                    //上传图像信息
                    $("#personOtherProve").val(file.filePath);
                    $("#personOtherProve_md5").val(file.fileMd5);
                    $("#personOtherProve_fileName").val(file.fileName);
                }else if("cod_cust_lincense_img" == imgType){
                    //企业证件营业执照
                    $("#errortop").html("证件正面上传成功");
                    var img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    $("#img_cod_cust_lincense_img").html(img);
                    //上传图像信息
                    $("#cod_cust_lincense_img").val(file.filePath);
                    $("#cod_cust_lincense_md5").val(file.fileMd5);
                    $("#cod_cust_lincense_fileName").val(file.fileName);
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"cust","businessLicense","","oss","n","n",file.filePath);
                    $("#FilesLinkBusinessEntity").val("["+addProImg+"]");
                }else if("cod_org_no_img" == imgType){
                    //企业证件组织机构代码
                    $("#errortop").html("证件正面上传成功");
                    var img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    $("#img_cod_org_no_img").html(img);
                    //上传图像信息
                    $("#cod_org_no_img").val(file.filePath);
                    $("#cod_org_no_img_md5").val(file.fileMd5);
                    $("#cod_org_no_img_fileName").val(file.fileName);
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"cust","organizationCode","","oss","n","n",file.filePath);
                    $("#FilesLinkBusinessEntity").val("["+addProImg+"]");
                }else if("corpOtherProve" == imgType){
                    //企业证件其他证件图片
                    $("#errortop").html("证件正面上传成功");
                    var img = '<img src="' + file.filePath + '" width="640" height="382"/>';
                    $("#img_corpOtherProve").html(img);
                    //上传图像信息
                    $("#corpOtherProve").val(file.filePath);
                    $("#corpOtherProve_md5").val(file.fileMd5);
                    $("#corpOtherProve_fileName").val(file.fileName);
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"cust","corpOtherProve","","oss","n","n",file.filePath);
                    $("#FilesLinkBusinessEntity").val("["+addProImg+"]");
                }else if("topic_img" == imgType){//项目社区上传图片

                    var img='<div class="up-img"><img src="' + file.filePath + '"  alt=""/></div><div class="up-delete"><a href="javascript:;"  onclick="deleteImg(\''+file.fileMd5+'\')"><img src="${Presource}/images/icon-delete.png"  alt=""/></a>';
                    $("#topicImg").html(img);
                    $("#img_topic").val(file.filePath);
                }else if("equit_add01_logoImg"==imgType){  //股权众筹  上传Logo图片
                    var  img='<img src="' + file.filePath + '" width="105" height="70" style="margin: 5px 0 0px 0px;"/>';
                    if(bid_type!=null){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用
                            deleteFile(imgType,"");
                            $("#cod_business_subNum").val('1');
                        }
                    }
                    deleteEquit("businessLogo");//单张图片点击多次上传
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"business","businessLogo","","oss","y","n",file.filePath);
                    $("#FilesLinkBusinessEntity01").val("["+addProImg+"]");
                    $("#true_equit_add01_logoImg").val(file.filePath);//在添加的时候单独验证的
                    $("#project_logo_span").html(img);//股权众筹  上传项目大图
                }else if("equit_add01_logoImg2"==imgType){
                    var  img='<img src="' + file.filePath + '"  width="1420" height="566" style="margin: 2px 0 0px 0px;"/>';
                    if(bid_type!=null){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用
                            deleteFile(imgType,"");
                            $("#cod_business_subNum").val('1');
                        }
                    }
                    deleteEquit("contentPic");//单张图片点击多次上传
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"business","contentPic","","oss","y","n",file.filePath);
                    $("#FilesLinkBusinessEntity01").val("["+addProImg+"]");
                    $("#true_equit_add01_logoImg2").val(file.filePath);
                    $("#project_logo_span2").html(img);//股权众筹  上传项目大图

                }else if("equit_add02_logoImg"==imgType){//股权众筹  上传相册  第二步
                    var  img='<li  id="'+file.fileMd5+'"><img src="' + file.filePath + '"/><span class="pos-opt"><a href="javascript:;"   onclick="deleteFile(\''+file.fileMd5+'\',\'1\')"><img src="${Presource}/images/icon-delete.png"  alt=""/></a></span></li>';
                    if(bid_type!=null){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用
                            $("#cod_business_subNum02").val("1");
                        }
                    }
                    $(".equit_photo_img").hide();
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"business","album","","oss","n","y",file.filePath);
                    $("#FilesLinkBusinessEntity02").val("["+addProImg+"]");
                    $("#true_add02_logoImg").val(file.filePath);
                    $("#gradePicture").append(img);
                }else  if("equit_add04_team"==imgType){//股权众筹 上传团队成员图片  第四步
                    var  img='<img src="' + file.filePath + '" width="640" height="382" style="margin: 5px 0 0px 0px;"/>';
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"team","teamMemberAvatar","","oss","y","n",file.filePath);
                    if(bid_type!=null){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用

                        }
                    }
                    deleteEquitByfileMd5(file.fileMd5);//在添加多名成员时  删除前面
                    var team_num=$("#team_num").val();
                    $("#FilesLinkBusinessEntity"+team_num).val("["+addProImg+"]");
                    $("#project_team_span"+team_num).html(img);

                }else if("equit_add05_pic"==imgType){//股权众筹 第五步上传营业执照
                    var  img='<img src="' + file.filePath + '" width="105" height="70" style="margin: 5px 0 0px 0px;"/>';
                    if(bid_type!=null){
                        if(bid_type=="Edit"){//当处于修改状态时  先删除数据   在第一步的时候使用
                            deleteFile(imgType,"");
                            $("#cod_business_subNum").val('1');
                        }
                    }
                    deleteEquit("businessLicense");//单张图片点击多次上传
                    addGradeInformation(file.fileMd5,'${Img_filePath}'+file.fileName,file.fileName,"cust","businessLicense","","oss","y","n",file.filePath);
                    $("#FilesLinkBusinessEntity05").val("["+addProImg+"]");
                    $("#true_equit_add05_pic").val(file.filePath);
                    $("#bussiness_license_span").html(img);//在添加的时候单独验证的
                }



                $(".uploadphoto-popup").addClass("s05x");
                $(".mfp-bg").fadeOut(300);
            }else{
                showErrorTip(data);
                $(".uploadphoto-popup").addClass("s05x");
                $(".mfp-bg").fadeOut(300);
            }
        },
        error:function(){
            $("#errortop").show();
            $(".uploadphoto-popup").addClass("s05x");
            $(".mfp-bg").fadeOut(300);
        }
    });
}

//将一个表单的数据返回成JSON对象
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};