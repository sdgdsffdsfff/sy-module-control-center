
<#include "page_header.ftl" > 

      <!-- Main component for a primary marketing message or call to action -->
      <div style="min-height: 100px;" class="control_center_main_area"
       auto_load="<#if target?? >false<#else>true</#if>" >
      	${testTarget! } 
      </div>

<#include "page_footer.ftl" > 