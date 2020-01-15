<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.offer.list.label.title" path="title" width="10%"/>
	<acme:list-column code="authenticated.offer.list.label.moment" path="moment" width="15%"/>
	<acme:list-column code="authenticated.offer.list.label.deadline" path="deadline" width="15%"/>
	<acme:list-column code="authenticated.offer.list.label.text" path="text" width="15%"/>
	<acme:list-column code="authenticated.offer.list.label.minReward" path="minReward" width="10%"/>
	<acme:list-column code="authenticated.offer.list.label.maxReward" path="maxReward" width="10%"/>
	<acme:list-column code="authenticated.offer.list.label.ticker" path="ticker" width="10%"/>
</acme:list>