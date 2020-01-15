<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title"/>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="consumer.offer.form.label.text" path="text"/>
	<acme:form-panel code="consumer.offer.form.panel.reward">
	<acme:form-money code="consumer.offer.form.label.minReward" path="minReward"/>
	<acme:form-money code="consumer.offer.form.label.maxReward" path="maxReward"/>
	</acme:form-panel>
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker"/>
	<acme:form-checkbox code="consumer.offer.form.label.confirm" path="confirm"/>
	
	<acme:form-submit code="consumer.offer.form.button.create" action="/consumer/offer/create"/>
	<acme:form-return code="consumer.offer.form.button.return"/>
</acme:form>