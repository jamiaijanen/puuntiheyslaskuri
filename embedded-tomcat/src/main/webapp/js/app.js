async function removeProduct(id) {
			let response = await fetch(`/prosivu?id=${id}` , { method: "DELETE"});

			if (response.status === 200) {
				let element = document.getElementById("BalsaItem-" + id);
				element.remove();
			} else {
				alert("Something went wrong");
			}
		}