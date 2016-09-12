<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Railway DC</title>

	<script> 
		$(document).ready(function() {
			$(".accordion").hideShowPlugin();
			$(".addField").addFieldPlugin();
			$(".removeField").removeFieldPlugin();
		});
	</script>
	
</head>
</body>
	<form action="/schedule/" method="get" accept-charset="utf-8">
				<h2 align="center">РАСПИСАНИЕ ДВИЖЕНИЯ &nbsp;</h2>
  		<input type="hidden" name="command" value="search"/>
  		<%-- СПИСОК СТАНЦИЙ --%>
  		<datalist id="stations">
			<option label="Минск-Пассажирский" value="Минск-Пассажирский">
			<option label="Минск-Восточный" value="Минск-Восточный">
			<option label="парк Заводская" value="Тракторный">
			<option label="Степянка" value="Степянка">
			<option label="Озерище" value="Озерище">
			<option label="Колодищи" value="Колодищи">
			<option label="Городище" value="Городище">
			<option label="Смолевичи" value="Смолевичи">
			<option label="Красное знамя" value="Красное знамя">
			<option label="Жодино" value="Жодино">
			<option label="Шеметово" value="Национальный аэропорт">
			<option label="Борисов" value="Борисов">
			<option label="Помыслище" value="Помыслище">
			<option label="Фаниполь" value="Фаниполь">
			<option label="Койданово" value="Койданово">
			<option label="Негорелое" value="Негорелое">
			<option label="Минск-Южный" value="Минск-Южный">
			<option label="Колядичи" value="Колядичи">
			<option label="Асеевка" value="Асеевка">
			<option label="Михановичи" value="Михановичи">
			<option label="Руденск" value="Руденск">
			<option label="Пуховичи" value="Пуховичи">
			<option label="Талька" value="Талька">
			<option label="Минск-Северный" value="Минск-Северный">
			<option label="Ждановичи" value="Ждановичи">
			<option label="Ратомка" value="Ратомка">
			<option label="Крыжовка" value="Крыжовка">
		</datalist>		
		<div class="row">
			<div class="span4">
				<label class="control-label" for="bStation">Откуда </label>
				<div class="controls">
					<input class="input-xlarge" type="search" name="bStation" list="stations" required value="${param.bStation}" placeholder="название станции" />
				</div>
			</div>
			<div class="span4">
				<label class="control-label" for="eStation">Куда</label>
				<div class="controls">
		    		<input class="input-xlarge" type="search" name="eStation" list="stations" required value="${param.eStation}" placeholder="название станции" /> 		
		    	</div>
			</div>
			<div class="span4">
				<label class="control-label" for="date_btn">Когда</label>
				<div class="controls">
					<div class="input-append">
			   			<input class="input" type="date" name="date" required value="${param.date}" placeholder="yyyy-MM-dd" />      
			   			<input class="btn" type="submit" value="Найти" />
			   		</div>
		   		</div>
			</div>
		</div>
		
		<div class="row">
			<div class="span2">
				<label class="control-label" for="bTime">От</label>
				<div class="controls">
					<input class="span2" type="time" name="bTime" required value="${param.bTime}" placeholder="hh:mm" />
				</div>
			</div>
			<div class="span2">
				<label class="control-label" for="eTime">До</label>								
				<div class="controls">
			        <input class="span2 input-xlarge" type="time" name="eTime" required value="${param.eTime}" placeholder="hh:mm" />
				</div>
			</div>
		</div>
		
			
		<dl class="accordion">
		    <dt class='btn'><i class='icon-pencil'></i>Промежуточные станции</dt>
	    	<dd>
	    		<p>
	    			<div class="">
		    			<input class="addField btn btn-primary" type="submit" value="Добавить"/>
						<input class="removeField btn" type="submit" value="Удалить"/>
					</div>
					<br>
					<div class="row-fluid">
						<div class="span3">
				    		<table class="intermediate-stations table table-striped table-bordered table-hover table-condensed">					    			
				    			<thead>
				    				<tr>
					    				<td>Station</td>
					    				<td>Time</td>
					    			</tr>
				    			</thead>
				    			<tbody>
									<c:forEach begin="1" end='${fn:length(paramValues.s)}' step="1" varStatus="status">
										<tr>
											<td><input class='input-search' name='s' value='${paramValues.s[status.count - 1]}' required list='stations' placeholder='название станции' type='search'/></td>
											<td><input class='input-small' name='d' value='${paramValues.d[status.count - 1]}' type='search' placeholder='время в минутах' step='5' /></td>
										</tr>
									</c:forEach> 
								</tbody>
							</table>
						</div>
					</div>
	    	</dd>
		</dl>
  	</form> 								
			
			
	<jsp:include page="/WEB-INF/jsp/routesView.jsp"></jsp:include>
			
</body>
</html>