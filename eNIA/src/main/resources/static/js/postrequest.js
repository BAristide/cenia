$(document).ready(
		function() {

			// SUBMIT FORM
			$("#postAvaluate").click(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					baseURL : $("#baseURL").val()
				}
console.log(formData);
				// DO POST
				$.ajax({
					type : "POST",
					contentType: "application/json",
					url : "/admin/evaluate",
					data : JSON.stringify(formData),
					dataType : 'json',
					cache: false,
					timeout: 600000,
					success : function(data) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									"" +  "Post Successfully! <br>"
											+ "---> Congrats !!" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

		})