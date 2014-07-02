<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>微信服务框架-控制中心</title>
    <base href="${request_context.basePath}module/" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <@res resid="bootstrap" include=".*\\.css" />
    <@res resid="jquery" />
	
	<style>
		body {
		  min-height: 2000px;
		  padding-top: 70px;
		}
	</style>
	
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">sy-module控制中心</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          	
          	<#list menus as menu>
				<#if menu.subMenu?size <= 0 >
					<li>
						<a href="javascript:void(0);" linkUrl="${menu.linkUrl }" linkType="${menu.linkType}" class="control_center_menu" >${menu.title }</a>
					</li>
				<#else>
            		<li class="dropdown">
              			<a href="#" class="dropdown-toggle" data-toggle="dropdown">${menu.title }<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<#list menu.subMenu as subMenu >
								<li><a href="javascript:void(0);" linkUrl="${subMenu.linkUrl }" linkType="${subMenu.linkType}" class="control_center_menu" >${subMenu.title }</a></li>
          					</#list> 
						</ul>
					</li>
				</#if>
          	</#list> 
          	
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">
    
    
    	<div class="control_center_main_body" >
    