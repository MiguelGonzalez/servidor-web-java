var AccionesLogin = {
	acceder : function() {
		var password = $("#passwordLogin").val();
		var name = $("#nameLogin").val();

		$.ajax({
			type: "POST",
			url: '/admin/login',
			data: { 'name': name, 'password': password },
			dataType: "json"
		}).done(function( msg ) {

			console.log(msg);

			if(msg.successful) {
				$("#modalLoginOk").modal('show');
				console.log("Logueado");
			} else {
				$("#modalLoginError").modal('show');
				console.log("Error login");
			}
		});

	},
	reloadPageAdmin: function() {
		window.location.href= "/admin/";
	}
 }