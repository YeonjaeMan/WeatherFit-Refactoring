let str1 = '00';

(function() {
	let today = new Date();

	let year = today.getFullYear();
	let month = ('0' + (today.getMonth() + 1)).slice(-2);
	let day = ('0' + today.getDate()).slice(-2);
	let base_date = year + month + day;

	let hours = ('0' + today.getHours()).slice(-2);
	let minutes = ('0' + today.getMinutes()).slice(-2);
	if (Number(minutes) >= 45) {
		minutes = '30';
	} else {
		if (Number(hours) == 0) {
			hours = '23';
		} else {
			hours = ('0' + (Number(hours) - 1)).slice(-2);
		}
		minutes = '30';
	}
	let base_time = hours + minutes;
	console.log(base_date);
	console.log(base_time);

	$.ajax({
		url: `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?serviceKey=0pAM3mq68Ft5YCoKoMe5CiXjNZiB9IiJoMUxqUKsMS897TRFS2YC0ra%2BHpo7VZpMrKmqrAGUuA5TPySKNoAAcQ%3D%3D&numOfRows=60&base_date=${base_date}&base_time=${base_time}&nx=58&ny=73&dataType=JSON`,
		type: "GET",

		success: function(res) {
			console.log(res);
			//console.log(res.response.body.items.item[24].fcstValue); // 3/20 10:30 기준 11:00 기온(7)
			// console.log(res.response.body.items.item[12].fcstValue); // 강수량("강수없음")
			//console.log(res.response.body.items.item[18].fcstValue); // 하늘상태(1) 0~5 맑음 6~8 구름많음 9~10 흐림
			// console.log(res.response.body.items.item[30].fcstValue); // 습도(40%)
			// console.log(res.response.body.items.item[6].fcstValue); // 강수형태(0) 0 없음 1 비 2 비/눈 3 눈 5 빗방울 6 빗방울눈날림 7 눈날림
			// console.log(res.response.body.items.item[54].fcstValue); // 풍속(3) 4미만 - 바람이 약하다 / 4이상~9미만 - 바람이 약간 강하다 / 9이상~14미만 - 바람이 강하다 / 14이상 - 바람이 매우 강하다
			let T1H = res.response.body.items.item[24].fcstValue;
			let RN1 = res.response.body.items.item[12].fcstValue;
			let SKY = res.response.body.items.item[18].fcstValue;
			let REH = res.response.body.items.item[30].fcstValue;
			let PTY = res.response.body.items.item[6].fcstValue;
			let WSD = res.response.body.items.item[54].fcstValue;
			if (SKY >= 0 && SKY <= 5) {
				$("#weather-sky").append('<i class="fa-solid fa-sun"></i>');
			} else if (SKY >= 6 && SKY <= 8) {
				$("#weather-sky").append('<i class="fa-solid fa-cloud-sun"></i>');
			} else if (SKY >= 9 && SKY <= 10) {
				$("#weather-sky").append('<i class="fa-solid fa-cloud"></i>')
			}
			$("#weather-t1h").append("<span>" + T1H + "°C</span>")
			//$("#weather-list").append("<li>" + "기온 : " + T1H + "도" + "</li>");
			//$("#weather-list").append("<li>" + "강수량 : " + RN1 + "</li>");
			//$("#weather-list").append("<li>" + "하늘상태 : " +  SKY + "</li>");
			//$("#weather-list").append("<li>" + "습도 : " + REH + "</li>");
			//$("#weather-list").append("<li>" + "강수형태 : " + PTY + "</li>");
			//$("#weather-list").append("<li>" + "풍속 : " + WSD + "</li>");
		},

		error: function(err) {
			console.log("요청 중 에러 발생");
		}
	})
}());