<#macro enumSelect selectName enumValues>
    <select name="${selectName}" class="form-control" id="exampleFormControlSelect1">
        <#list enumValues as enum>
            <option value="${enum}">${enum}</option>
        </#list>
    </select>
</#macro>