<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="schedule" class="by.mpc.web.view.model.ScheduleView" scope="request"></jsp:useBean>

<div class="container">
	<c:choose>
		<c:when test="${schedule.size() eq 0}">
			<i>Введите данные и нажмите кнопку Найти</i>
		</c:when>
		<c:otherwise>
			<label class="control-label" for="filter">Сортировка</label>
			<select name="sort">
				<option label="">Номер</option>
				<option>Отправление</option>
				<option>Прибытие</option>
				<option>Время в пути</option>
				<option>Время на станции</option>
				<option>Общее время</option>
			</select>
			<table class="table table-condensed table-hover table-bordered">
				<thead>
					<tr>
						<td>№</td>
						<td>Маршрут</td>
						<td>Отправление</td>
						<td>Прибытие</td>
						<td>Время в пути</td>
						<td>Время на станции</td>
						<td>Общее время</td>
					</tr>
				</thead>
		
				<c:forEach items="${schedule.getList()}" var="item" begin="0" step="1" varStatus="counter">
					<tbody>
						<tr class="info">
							<td>${item.getExternRoute().getNumberTrain()}</td>
							<td><i>${item.getExternRoute().getBStation().concat(" - ").concat(item.getExternRoute().getEStation())}</i></td>
							<td><i><b>${item.getExternRoute().getBTimeString("HH:mm")}</b></i></td>
							<td><i><b>${item.getExternRoute().getETimeString("HH:mm")}</b></i></td>
							<td colspan="3"></td>
						</tr>
						<c:forEach items="${item.getList()}" var="it">
							<tr>
								<td>
									<i class="train_type ${it.getTypeTrain()}"></i>	
									<small>${it.getNumberTrain()}</small> 	
								</td>
								<td>${it.getBStation().concat(" - ").concat(it.getEStation())}</td>
								<td>${it.getBTimeString("HH:mm")}</td>
								<td>${it.getETimeString("HH:mm")}</td>
								<td>${it.getTextTime(it.getTrainTime())}</td>
								<td>${it.getTextTime(it.getStationTime())}</td>
								<td>${it.getTextTime(it.getTotalTime())}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4"></td>
							<td><i><b>${item.getExternRoute().getTextTime(item.getExternRoute().getTrainTime())}</b></i></td>
							<td><i><b>${item.getExternRoute().getTextTime(item.getExternRoute().getStationTime())}</b></i></td>
							<td><i><b>${item.getExternRoute().getTextTime(item.getExternRoute().getTotalTime())}</b></i></td>
						</tr>
					</tbody>
				</c:forEach>
				
			</table>		
		</c:otherwise>
	</c:choose>		
</div>


