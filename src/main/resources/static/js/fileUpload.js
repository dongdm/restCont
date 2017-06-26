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
//文件上传
function file_upload(flag){
    clearCutImg();
    var fileElementId = "data_file";
    ajaxFileUpload(fileElementId,"file_upload_bk",flag, 'file');
}

//文件删除
function file_del(obj){
    var currTr = $(obj).parent().parent();
    var id = $(obj).attr("data-id");
    var url = "/file/del";
    var data = {"id": id};
    $.ajax({
        url: url,
        data: data,
        type: 'POST',
        dataType: 'text',
        success: function (data) {
            console.log(data);
            var msg = eval('(' + data + ')');
            var replyCode = msg.trans.replyCode;
            if (replyCode == "0000") {
                currTr.remove();
            }else{
                console.error("信息保存失败")
            }
        }
    });
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

    imgAreaSelectApi = $('.pup-photo img').imgAreaSelect({
        x1: 0, y1: 0, x2: width, y2: height,
        handles : true,// true，调整手柄则会显示在选择区域内
        show : true,    // 选区会显示
        persistent : true,  // true，选区以外点击不会启用一个新选区（只能移动/调整现有选区）
        resizable : true,   // true， 选区面积可调整大小
        fadeSpeed : 200,
        instance : true,
        onSelectChange : preview
    });
}

//附件上传的回调函数
function file_upload_bk(file, fileElementId){
    var html = '<div>'
             + '<div style="width: 80%;float: left;height: 30px;">' + file.fileName + '</div>'
             + '<div style="width: 15%;float: left;height: 30px;">'
             +   '<a href="' + file.filePath + '">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;'
             +   '<a href="javascript:void(0);" onclick="file_del(this);" data-id="' + file.id + '">删除</a>'
             + '</div>'
             + '</div>';
    $('#fileList').prepend(html);
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
    $("input[name='height']").val(selection.height);
    $("input[name='width']").val(selection.width);
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
    console.info(param);
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
                if("img_intro" == imgType){
                    //用户头像
                    $("#errortop").html("证件照上传成功");
                    var img = '<div class="head-portrait"><img src="' + file.filePath + '"/></div>';
                    $("#minimglist").append(img);
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

function cancleImgBox(){
    $(".uploadphoto-popup").addClass("s05x");
    $(".mfp-bg").fadeOut(300);
    //清除可选区域
    clearCutImg();
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