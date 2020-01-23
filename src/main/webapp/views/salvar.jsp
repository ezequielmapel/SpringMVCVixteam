<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Salvar Pessoa</title>
	</head>
	<body>
		<form action="/teste/helloWorld/salvarPessoa" method="POST">
			<input type="text" name="nome" id="nome" placeholder="Nome"><br>
			<button type="submit">Enviar</button>
		</form>
		
		<c:forEach items="${pessoas}" var="pessoa">
			${pessoa}<br>
		</c:forEach>
		
	</body>
</html>