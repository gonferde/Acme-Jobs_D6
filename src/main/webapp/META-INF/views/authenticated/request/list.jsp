<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.request.list.label.title" path="title" width="16%"/>
	<acme:list-column code="authenticated.request.list.label.moment" path="moment" width="16%"/>
	<acme:list-column code="authenticated.request.list.label.deadline" path="deadline" width="16%"/>
	<acme:list-column code="authenticated.request.list.label.text" path="text" width="16%"/>
	<acme:list-column code="authenticated.request.list.label.reward" path="reward" width="16%"/>
	<acme:list-column code="authenticated.request.list.label.ticker" path="ticker" width="16%"/>
</acme:list>