<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="worker.job.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-textbox code="worker.job.form.label.title" path="title"/>
	<acme:form-moment code="worker.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="worker.job.form.label.salary" path="salary"/>
	<acme:form-url code="worker.job.form.label.moreInfo" path="moreInfo"/> 
	
	<acme:form-submit test="${command == 'show'}"
		code="worker.job.form.button.apply"
		action="/worker/application/create?idJob=${id}"
		method="get"/>
	
	
	<acme:form-return code="worker.job.form.button.return"/>
</acme:form>