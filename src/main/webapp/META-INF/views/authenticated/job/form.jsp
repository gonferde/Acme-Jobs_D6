<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.job.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-textbox code="authenticated.job.form.label.status" path="status"/>
	<acme:form-textbox code="authenticated.job.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="authenticated.job.form.label.salary" path="salary"/>
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-panel code="authenticated.job.form.label.descriptor">
		<acme:form-textarea code="authenticated.job.form.label.descriptor.description" path="descriptor.description"/>
	</acme:form-panel>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/duty/list-mine?id=${descriptor.id}')"
			class="btn btn-default">
			<acme:message code="authenticated.job.form.label.dutyButton"/>
	</button>
	<acme:form-return code="authenticated.job.form.button.return"/>
</acme:form>