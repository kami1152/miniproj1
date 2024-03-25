const formToSerialize = (formId) => JSON.stringify([].reduce.call(document.querySelector('#' + formId), (data, element) => {
	    console.log(element);
	    if (element.name == '') return data;
	    if (element.type == 'radio' || element.type == 'checkbox') {
	        if (element.checked) {
	           if (typeof data[element.name] == 'undefined') {
	              
	                data[element.name] = element.value;
	           } else if(typeof data[element.name] == 'string') {
	               
	              data[element.name] = [data[element.name], element.value]; 
	           } else if(typeof data[element.name] == 'object') {
	                
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
			method:"POST",
			body : param,
			headers : {"Content-type" : "application/json; charset=utf-8"}
	}).then(res => res.json()).then(json => {
		console.log("json ", json );
		if (handler) handler(json);
	});	
}	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 