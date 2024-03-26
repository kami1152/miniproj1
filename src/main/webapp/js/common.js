const formToSerialize = (formId) => JSON.stringify([].reduce.call(document.querySelector('#' + formId), (data, element) => {
	console.log(element);
	if (element.name == '') return data;
	if (element.type == 'radio' || element.type == 'checkbox') {
		if (element.checked) {
			if (typeof data[element.name] == 'undefined') {

				data[element.name] = element.value;
			} else if (typeof data[element.name] == 'string') {

				data[element.name] = [data[element.name], element.value];
			} else if (typeof data[element.name] == 'object') {

				data[element.name].push(element.value);
			}
		}
	} else {

		data[element.name] = element.value;
	}
	return data;
},
	{} //초기값 
)
);


const myFetch = (url, formId, handler) => {
	const param = typeof formId == "string" ? formToSerialize(formId) : JSON.stringify(formId);
	fetch(url, {
		method: "POST",
		body: param,
		headers: { "Content-type": "application/json; charset=utf-8" }
	}).then(res => res.json()).then(json => {
		console.log("json ", json);
		if (handler) handler(json);
	});
}


function loadNavigation() {
	fetch('navigation.html')
		.then(response => response.text())
		.then(data => {
			document.getElementById('navigation').innerHTML = data;
		})
		.catch(error => {
			console.error('네비게이션 로드 중 오류가 발생했습니다:', error);
		});
}

function loadNavigation2() {
	fetch('navigation2.html')
		.then(response => response.text())
		.then(data => {
			document.getElementById('navigation2').innerHTML = data;
		})
		.catch(error => {
			console.error('네비게이션 로드 중 오류가 발생했습니다:', error);
		});
}


function jsLogout() {
		
		myFetch("user.do", {action : "logout"}, json => {
			if(json.status == 0) {
				//성공
				alert("로그아웃되었습니다");
				location = "board.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});
				
	}


loadNavigation();
loadNavigation2();













