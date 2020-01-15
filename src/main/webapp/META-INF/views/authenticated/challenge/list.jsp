<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.challenge.list.label.title" path="title" />
	<acme:list-column code="authenticated.challenge.list.label.description" path="description" />
	<acme:list-column code="authenticated.challenge.list.label.deadLine" path="deadLine" />
	<acme:list-column code="authenticated.challenge.list.label.goldGoal" path="goldGoal" />
	<acme:list-column code="authenticated.challenge.list.label.goldReward" path="goldReward" />
	<acme:list-column code="authenticated.challenge.list.label.silverGoal" path="silverGoal" />
	<acme:list-column code="authenticated.challenge.list.label.silverReward" path="silverReward"/>
	<acme:list-column code="authenticated.challenge.list.label.bronzeGoal" path="bronzeGoal" />
	<acme:list-column code="authenticated.challenge.list.label.bronzeReward" path="bronzeReward" />
</acme:list>

