<#global basePath="${request.getContextPath()}">
<#macro page title>
<!DOCTYPE html>
<html lang="zh-CN">

<!-- Head BEGIN -->
<head>
  	<meta charset="utf-8">
	<title>${title?html}</title>
	
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="robots" content="nofollow" />

  	<meta name="description" content="Metronic Shop UI description">
  	<meta name="keywords" content="Metronic Shop UI keywords">
  	<meta name="author" content="keenthemes">
	
	<link rel="shortcut icon" href="${basePath}/images/favicon.ico" />
	
	<!-- Fonts START -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">
	<!-- Fonts END -->

	<!-- Global styles START -->
	<link href="${basePath}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${basePath}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Global styles END -->
	
	<!-- Page level plugin styles START -->
	<link href="${basePath}/assets/global/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet">
	<link href="${basePath}/assets/global/plugins/carousel-owl-carousel/owl-carousel/owl.carousel.css" rel="stylesheet">
	<link href="${basePath}/assets/global/plugins/slider-revolution-slider/rs-plugin/css/settings.css" rel="stylesheet">
	<!-- Page level plugin styles END -->

	<!-- Theme styles START -->
	<link href="${basePath}/assets/global/css/components.css" rel="stylesheet">
	<link href="${basePath}/assets/frontend/layout/css/style.css" rel="stylesheet">
	<link href="${basePath}/assets/frontend/pages/css/style-revolution-slider.css" rel="stylesheet"><!-- metronic revo slider styles -->
	<link href="${basePath}/assets/frontend/layout/css/style-responsive.css" rel="stylesheet">
	<link href="${basePath}/assets/frontend/layout/css/themes/red.css" rel="stylesheet" id="style-color">
	<link href="${basePath}/assets/frontend/layout/css/custom.css" rel="stylesheet">
	<!-- Theme styles END -->
	
	<#-- 用于外部js文件中使用 -->
	<script type="text/javascript">
		var basePath="${basePath}";
	</script>
	
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="corporate">
	<#-- 顶部导航栏Start -->
	<header>
		<#include "/common/header.ftl" />
	</header>
	<#-- 顶部导航栏End-->
	
	<#-- 内容Start -->
	<main>
		<#nested>
	</main>
	<#-- 内容end -->
	
	<#-- 底部Start -->
	<footer>
		<#include "/common/footer.ftl" />
	</footer>
	<#-- 底部end -->
	
    <!-- Load javascripts at bottom, this will reduce page load time -->
    <!-- BEGIN CORE PLUGINS (REQUIRED FOR ALL PAGES) -->
    <!--[if lt IE 9]>
    <script src="${basePath}/assets/global/plugins/respond.min.js"></script>
    <![endif]--> 
    <script src="${basePath}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${basePath}/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
    <script src="${basePath}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      
    <script src="${basePath}/assets/frontend/layout/scripts/back-to-top.js" type="text/javascript"></script>
    <!-- END CORE PLUGINS -->

    <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
    <script src="${basePath}/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
    <script src="${basePath}/assets/global/plugins/carousel-owl-carousel/owl-carousel/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->

    <!-- BEGIN RevolutionSlider -->  
    <script src="${basePath}/assets/global/plugins/slider-revolution-slider/rs-plugin/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script> 
    <script src="${basePath}/assets/global/plugins/slider-revolution-slider/rs-plugin/js/jquery.themepunch.tools.min.js" type="text/javascript"></script> 
    <script src="${basePath}/assets/frontend/pages/scripts/revo-slider-init.js" type="text/javascript"></script>
    <!-- END RevolutionSlider -->

    <script src="${basePath}/assets/frontend/layout/scripts/layout.js" type="text/javascript"></script>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            Layout.init();    
            Layout.initOWL();
            RevosliderInit.initRevoSlider();
            Layout.initTwitter();
            Layout.initFixHeaderWithPreHeader(); /* Switch On Header Fixing (only if you have pre-header) */
            Layout.initNavScrolling();
        });
    </script>
    <!-- END PAGE LEVEL JAVASCRIPTS -->
	
</body>
<!-- END BODY -->
</html>
</#macro>