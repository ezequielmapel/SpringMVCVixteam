<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Clientes</title>
		<link rel="stylesheet" href="/public/css/style.css">
		
		<style>
			body{
	padding:0px;
	margin:0px;
	height:100vh;
	font-family:'Arial', sans-serif;
}body{
	padding:0px;
	margin:0px;
	height:100vh;
	background-color:#fff;
}

.container{
	width:100%;
	height:100%;
}

.formSend{
	height:300px;
	width: 400px;
	margin: 30px auto 0;
	display:flex;
	flex-direction:column;
	align-items:center;
	justify-content:center;
	font-size: 11px;
	background-color:#5f579a;

}

.formSend form{
	
	
}

.button{
	width: 80px;
	height: 40px;
	border:0px;
	margin-top: 20px;
	background-color:black;
	color:white;
	cursor:pointer;
	font-weight:600;
	border-radius:1px;
}

.formSend .inp{
	padding: 7px;
	border:0px;
	border: 1px solid black;
}

.button:hover{
	border:1px solid #5f579a;
	color:#5f579a;
	background-color:white;
}

/* ###################### */
.listClients{
	width: 450px;
	padding: 5px;
}
.listClients .inp{
	width:90%;
	border: 1px solid black;
	border-bottom:0;
	padding: 6px;
    background-color:#5f579a;
    color: white;
}
.listClients .inp::placeholder{
	color:white;
	font-weight:600;
}
.listClients button{
	border:1px solid black;
	width: 25px;
	
	padding:0px;
	height: 25px;
	background-color:black;
	color: white;
	margin:0;
	border-radius:100%;
}
.listClients table{
	background-color: white;
	width: 100%;
	padding:0px;
	border:1px solid black;
	margin-top:-1px;
	border-collapse: collapse;
}


.listClients table tr{
	border-bottom:1px solid black;
	padding: 0px;
}
.listClients table td{
	padding:0px;
	margin:0px;
	padding: 5px;
	
}


.listClients table tr:hover{
	background-color: gray;
	color:white;
	
}

.trChecked{
	background-color: blue;
	color:white;
}

			
		</style>
	</head>
	<body>
		<div class="container">
			<div class="formSend">
				<form>
					<input class="inp" type="text" name="name" id="name" placeholder="Nome do Cliente" value="${clienteEnviado.name}"><br><br>
					<label for="date" style="color:gray">Data de Nascimento:</label><br>
					<input class="inp" type="date" name="date" id="date" value="${clienteEnviado.date}">
					<br>
					<button id="buttonAlterar" class="button" type="button" >Alterar</button>
					<input class="button" type="submit" formmethod="POST" formaction="/teste/cliente/deletar" value="Deletar">
					<input class="button" type="submit" formmethod="POST" formaction="/teste/cliente/salvar" value="Salvar">
				</form>
					<div id="inputAlterarDiv" class="inputAlterar" style="display:none;">
					<br>
						<input  style="display:none;" class="inp" type="text" id="position" name="position" placeholder="Posição">
					</div>
					
					<p style="font-size:13px;font-weight:600;color:white;">&nbsp;${error}</p>
					
				
				
			</div>
			
			<div class="listClients">
				<form>
					<input class="inp" name="search" id="searchInp" type="text" placeholder="Pesquisar ...">
					<button type="submit">P</button>
				</form>
				<table>
					<c:forEach  var="cliente" items="${clientes}" varStatus="loop">
						<tr id="lItem${loop.index}"style="border-bottom:1px solid black;" onclick="itemListClicked(${loop.index})">
						<td>${loop.index}</td>
						<td>${cliente.name}</td>
						<td>${cliente.date}</td>
					</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
		
		<script type="text/javascript">
		const btnAlterar = document.querySelector("#buttonAlterar");
		btnAlterar.addEventListener('click', ()=>{
			
			const url = '/teste/cliente/alterar';
			const http = new XMLHttpRequest();
			const formData = new FormData();

			const inputName = document.querySelector("#name");
			const inputDate = document.querySelector("#date");
			const inputPos = document.querySelector("#position");

			formData.append("name",inputName.value);
			formData.append("date",inputDate.value);
			formData.append("position",inputPos.value);
			
			http.open("POST", url, true);
			http.send("nome="+inputName.value+"&"+"date="+inputDate.value+"&"+"position="+inputPos.value);

			if (http.readyState == 4 && http.status == 200) {
			    
				var data = http.responseText;
				
		    // Retorno do Ajax
				console.log(data);
			}
			
		});
		
		 function itemListClicked(loopIndex){
			 console.log(loopIndex);
			 const listItem = document.querySelector("#lItem"+loopIndex);
			 clearClicked();
			 listItem.classList.add("trChecked");

			 setValuesInput(listItem);
		}

		function clearClicked(){
			const table = document.querySelectorAll("table tbody tr");
			table.forEach((tr, index)=>{
				console.log(tr);
				tr.classList.remove("trChecked");
			});
		}

		function setValuesInput(listItem){
			const inputName = document.querySelector("#name");
			const inputDate = document.querySelector("#date");
			const inputPos = document.querySelector("#position");

			inputPos.value = listItem.children[0].textContent;
			inputName.value = listItem.children[1].textContent;
			//inputDate.value = listItem.children[2].textContent;
		}
		 
		</script>
	</body>
</html>