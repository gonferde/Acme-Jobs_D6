<%--
- form.jsp
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.garcia-roales-bulletin.label.name" path="name"/>
	<acme:form-textbox code="anonymous.garcia-roales-bulletin.label.surname" path="surname"/>
	<acme:form-textbox code="anonymous.garcia-roales-bulletin.label.uvus" path="uvus"/>
	<acme:form-moment code="anonymous.garcia-roales-bulletin.label.birth" path="birth"/>
	
	<acme:form-submit code="anonymous.garcia-roales-bulletin.button.create" action="/anonymous/garcia-roales-bulletin/create"/>
  	<acme:form-return code="anonymous.garcia-roales-bulletin.button.return"/>
</acme:form>