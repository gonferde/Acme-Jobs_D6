<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.duty.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.duty.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.duty.form.label.percentageTime" path="percentageTime"/>
	<acme:form-return code="authenticated.duty.form.button.return"/>
</acme:form>