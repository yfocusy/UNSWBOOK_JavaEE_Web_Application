<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>七牛云存储平台官方js上传</title>
    <link href="http://jssdk.demo.qiniu.io/images/favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="qnpupload/bootstrap.css">
    <link rel="stylesheet" href="qnpupload/main.css">
    <link rel="stylesheet" href="qnpupload/highlight.css">
    <script type="text/javascript" src="qnpupload/jquery.min.js"></script>
    <script type="text/javascript" src="qnpupload/bootstrap.min.js"></script>
    <script type="text/javascript" src="qnpupload/moxie.js"></script>
    <script type="text/javascript" src="qnpupload/plupload.dev.js"></script>
    <script type="text/javascript" src="qnpupload/zh_CN.js"></script>
    <script type="text/javascript" src="qnpupload/ui.js"></script>
    <script type="text/javascript" src="qnpupload/qiniu.js"></script>
    <script type="text/javascript" src="qnpupload/highlight.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>
    <script type="text/javascript">
        $(function() {
            var uploader = Qiniu.uploader({
                runtimes: 'html5,flash,html4',
                browse_button: 'pickfiles',
                container: 'container',
                drop_element: 'container',
                max_file_size: '1000mb',
                flash_swf_url: 'bower_components/plupload/js/Moxie.swf',
                dragdrop: true,
                chunk_size: '4mb',
                multi_selection: !(mOxie.Env.OS.toLowerCase()==="ios"),
                uptoken:'1o90vmY9OmRcueIeIeFBID6q2uy8peFiFBxpnM78:rdalpapK8yThJayH3FakQwqQbp8=:eyJzY29wZSI6InRlc3QiLCJkZWFkbGluZSI6MTYwNTA2NDI3MX0=',
                domain:'http://oleco2u3s.bkt.clouddn.com/',
                get_new_uptoken: false,
                auto_start: true,
                log_level: 5,
                init: {
                    'FilesAdded': function(up, files) {
                        $('table').show();
                        $('#success').hide();
                        plupload.each(files, function(file) {
                            //var objfile=$.parseJSON(file);
                            // alert(file.id);
                            // alert(file.getSource);
                            // console.log("file",file);
                            var progress = new FileProgress(file, 'fsUploadProgress');
                            progress.setStatus("等待...");
                            progress.bindUploadCancel(up);
                        });
                    },
                    'BeforeUpload': function(up, file) {
                        var progress = new FileProgress(file, 'fsUploadProgress');
                        var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                        if (up.runtime === 'html5' && chunk_size) {
                            progress.setChunkProgess(chunk_size);
                        }
                    },
                    'UploadProgress': function(up, file) {
                        var progress = new FileProgress(file, 'fsUploadProgress');
                        var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                        progress.setProgress(file.percent + "%", file.speed, chunk_size);
                    },
                    'UploadComplete': function() {
                        $('#success').show();
                    },
                    'FileUploaded': function(up, file, info) {
                        var progress = new FileProgress(file, 'fsUploadProgress');
                        progress.setComplete(up, info);
                    },
                    'Error': function(up, err, errTip) {
                        $('table').show();
                        var progress = new FileProgress(err.file, 'fsUploadProgress');
                        progress.setError();
                        progress.setStatus(errTip);
                    }
                }
            });

            uploader.bind('FileUploaded', function() {
                console.log('hello man,a file is uploaded');
            });
            $('#container').on(
                'dragenter',
                function(e) {
                    e.preventDefault();
                    $('#container').addClass('draging');
                    e.stopPropagation();
                }
            ).on('drop', function(e) {
                e.preventDefault();
                $('#container').removeClass('draging');
                e.stopPropagation();
            }).on('dragleave', function(e) {
                e.preventDefault();
                $('#container').removeClass('draging');
                e.stopPropagation();
            }).on('dragover', function(e) {
                e.preventDefault();
                $('#container').addClass('draging');
                e.stopPropagation();
            });



            $('#show_code').on('click', function() {
                $('#myModal-code').modal();
                $('pre code').each(function(i, e) {
                    hljs.highlightBlock(e);
                });
            });


            $('body').on('click', 'table button.btn', function() {
                $(this).parents('tr').next().toggle();
            });


            var getRotate = function(url) {
                if (!url) {
                    return 0;
                }
                var arr = url.split('/');
                for (var i = 0, len = arr.length; i < len; i++) {
                    if (arr[i] === 'rotate') {
                        return parseInt(arr[i + 1], 10);
                    }
                }
                return 0;
            };

            $('#myModal-img .modal-body-footer').find('a').on('click', function() {
                var img = $('#myModal-img').find('.modal-body img');
                var key = img.data('key');
                var oldUrl = img.attr('src');
                var originHeight = parseInt(img.data('h'), 10);
                var fopArr = [];
                var rotate = getRotate(oldUrl);
                if (!$(this).hasClass('no-disable-click')) {
                    $(this).addClass('disabled').siblings().removeClass('disabled');
                    if ($(this).data('imagemogr') !== 'no-rotate') {
                        fopArr.push({
                            'fop': 'imageMogr2',
                            'auto-orient': true,
                            'strip': true,
                            'rotate': rotate,
                            'format': 'png'
                        });
                    }
                } else {
                    $(this).siblings().removeClass('disabled');
                    var imageMogr = $(this).data('imagemogr');
                    if (imageMogr === 'left') {
                        rotate = rotate - 90 < 0 ? rotate + 270 : rotate - 90;
                    } else if (imageMogr === 'right') {
                        rotate = rotate + 90 > 360 ? rotate - 270 : rotate + 90;
                    }
                    fopArr.push({
                        'fop': 'imageMogr2',
                        'auto-orient': true,
                        'strip': true,
                        'rotate': rotate,
                        'format': 'png'
                    });
                }

                $('#myModal-img .modal-body-footer').find('a.disabled').each(function() {

                    var watermark = $(this).data('watermark');
                    var imageView = $(this).data('imageview');
                    var imageMogr = $(this).data('imagemogr');

                    if (watermark) {
                        fopArr.push({
                            fop: 'watermark',
                            mode: 1,
                            image: 'http://www.b1.qiniudn.com/images/logo-2.png',
                            dissolve: 100,
                            gravity: watermark,
                            dx: 100,
                            dy: 100
                        });
                    }

                    if (imageView) {
                        var height;
                        switch (imageView) {
                            case 'large':
                                height = originHeight;
                                break;
                            case 'middle':
                                height = originHeight * 0.5;
                                break;
                            case 'small':
                                height = originHeight * 0.1;
                                break;
                            default:
                                height = originHeight;
                                break;
                        }
                        fopArr.push({
                            fop: 'imageView2',
                            mode: 3,
                            h: parseInt(height, 10),
                            q: 100,
                            format: 'png'
                        });
                    }

                    if (imageMogr === 'no-rotate') {
                        fopArr.push({
                            'fop': 'imageMogr2',
                            'auto-orient': true,
                            'strip': true,
                            'rotate': 0,
                            'format': 'png'
                        });
                    }
                });

                var newUrl = Qiniu.pipeline(fopArr, key);

                var newImg = new Image();
                img.attr('src', 'images/loading.gif');
                newImg.onload = function() {
                    img.attr('src', newUrl);
                    img.parent('a').attr('href', newUrl);
                };
                newImg.src = newUrl;
                return false;
            });

        });
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-6" aria-expanded="false"></button>
            <a class="navbar-brand" href="">七牛云存储平台</a>
        </div>
    </div>
</nav>

<div class="container" style="padding-top: 60px;">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#" id="demo-tab" role="tab" data-toggle="tab" aria-controls="demo" aria-expanded="true">上传到云存储</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane fade in active" id="demo" aria-labelledby="demo-tab">
            <div class="row" style="margin-top: 20px;">
                <div class="col-md-12">
                    <div id="container" style="position: relative;">
                        <a class="btn btn-default btn-lg " id="pickfiles" style="position: relative; z-index: 1;"> <i class="glyphicon glyphicon-plus"></i> <span>选择文件</span>
                        </a>
                        <div id="html5_1alrdk7dpfo51dbm153n1jbsn0v3_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 171px; height: 46px; overflow: hidden; z-index: 0;">
                            <input id="html5_1alrdk7dpfo51dbm153n1jbsn0v3" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="">
                        </div>
                    </div>
                </div>
                <div style="display:none" id="success" class="col-md-12">
                    <div class="alert-success">队列全部文件处理完毕</div>
                </div>
                <div class="col-md-12 ">
                    <table class="table table-striped table-hover text-left" style="margin-top:40px;display:none">
                        <thead>
                        <tr>
                            <th class="col-md-4">文件名</th>
                            <th class="col-md-2">大小</th>
                            <th class="col-md-6">云存储信息</th>
                        </tr>
                        </thead>
                        <tbody id="fsUploadProgress">
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

    </div>
</div>



</body>
</html>
