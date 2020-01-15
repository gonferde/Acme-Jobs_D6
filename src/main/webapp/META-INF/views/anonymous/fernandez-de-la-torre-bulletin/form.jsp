<%--
- form.jsp
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.fernandez-de-la-torre-bulletin.label.moment" path="moment"/>
	<acme:form-textbox code="anonymous.fernandez-de-la-torre-bulletin.label.author" path="author"/>
	<acme:form-textbox code="anonymous.fernandez-de-la-torre-bulletin.label.text" path="text"/>
	<acme:form-moment code="anonymous.fernandez-de-la-torre-bulletin.label.language" path="language"/>

	<acme:form-submit code="anonymous.fernandez-de-la-torre-bulletin.form.button.create" action="/anonymous/fernandez-de-la-torre-bulletin/create"/>
  	<acme:form-return code="anonymous.fernandez-de-la-torre-bulletin.form.button.return"/>
</acme:form> 