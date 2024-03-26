let RE = 6371.00877; // 지구 반경(km)
let GRID = 5.0; // 격자 간격(km)
let SLAT1 = 30.0; // 투영 위도1(degree)
let SLAT2 = 60.0; // 투영 위도2(degree)
let OLON = 126.0; // 기준점 경도(degree)
let OLAT = 38.0; // 기준점 위도(degree)
let XO = 43; // 기준점 X좌표(GRID)
let YO = 136; // 기1준점 Y좌표(GRID)

const sunny = 1; // 맑음
const many_cloudy = 3; // 구름많음
const cloudy = 4; // 흐림

const none = 0; // 없음
const rain = 1; // 비
const snow_rain = 2; // 비/눈
const snow = 3; // 눈
const shower = 4; // 소나기
const raindrop = 5; // 빗방울
const raindrop_blowingsnow = 6; // 빗방울/눈날림
const blowingsnow = 7; // 눈날림

/*
*  날짜 변수
*/
let now = new Date();

let year = now.getFullYear();
let month = ('0' + (now.getMonth() + 1)).slice(-2);
let day = ('0' + now.getDate()).slice(-2);
let base_date = year + month + day;
console.log(base_date);
/*
* 시간 변수
*/
let hours = ('0' + now.getHours()).slice(-2);
let minutes = ('0' + now.getMinutes()).slice(-2);
if (Number(minutes) >= 45) {
	minutes = '30';
} else {
	if (hours == "00") {
		hours = '23';
	} else {
		hours = ('0' + (Number(hours) - 1)).slice(-2);
	}
	minutes = '30';
}
let base_time = hours + minutes;
console.log(base_time);

// 사용자 위치 정보 동의 / 비동의 처리 함수
if (navigator.geolocation) {
	navigator.geolocation.getCurrentPosition(success, error);
} else {
	alert("Geolocation is not supported by this browser.");
}

// 위도 경도 불러오기 성공!
function success(position) {
	let latitude = position.coords.latitude;
	let longitude = position.coords.longitude;
	console.log("Latitude: " + latitude + ", Longitude: " + longitude);

	let rs = dfs_xy_conv("toXY", latitude, longitude);
	console.log(rs);

	const API_KEY = "0pAM3mq68Ft5YCoKoMe5CiXjNZiB9IiJoMUxqUKsMS897TRFS2YC0ra%2BHpo7VZpMrKmqrAGUuA5TPySKNoAAcQ%3D%3D";

	$.ajax({
		url: `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?serviceKey=${API_KEY}&numOfRows=60&base_date=${base_date}&base_time=${base_time}&nx=${rs.x}&ny=${rs.y}&dataType=JSON`,
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

			$("#weather-t1h").append("<span>" + T1H + "°C</span>")
			//$("#weather-list").append("<li>" + "기온 : " + T1H + "도" + "</li>");
			//$("#weather-list").append("<li>" + "강수량 : " + RN1 + "</li>");
			//$("#weather-list").append("<li>" + "하늘상태 : " +  SKY + "</li>");
			//$("#weather-list").append("<li>" + "습도 : " + REH + "</li>");
			//$("#weather-list").append("<li>" + "강수형태 : " + PTY + "</li>");
			//$("#weather-list").append("<li>" + "풍속 : " + WSD + "</li>");

			if (PTY == 0) { // 강수형태 없음
				if (SKY >= 0 && SKY <= 5) { // 하늘상태 맑음
					if (6 < Number(hours) && Number(hours) < 18) {
						$("#weather-sky").append("<a><img alt='맑음' src='assets/images/sunny.svg' height='45px' title='맑음'/></a>");
					} else {
						$("#weather-sky").append("<a><img alt='맑음_저녁' src='assets/images/clear_night.svg' height='45px' title='맑음'/></a>");
					}
				} else if (SKY >= 6 && SKY <= 8) { // 하늘상태 구름많음
					if (6 < Number(hours) && Number(hours) < 18) {
						$("#weather-sky").append("<a><img alt='구름많음' src='assets/images/many_cloudy.svg' height='45px' title='구름많음'/></a>");
					} else {
						$("#weather-sky").append("<a><img alt='구름많음_저녁' src='assets/images/many_cloudy_night.svg' height='45px' title='구름많음'></a>");
					}
				} else if (SKY >= 9 && SKY <= 10) { // 하늘상태 흐림
					if (6 < Number(hours) && Number(hours) < 18) {
						$("#weather-sky").append("<a><img alt='흐림' src='assets/images/weather/cloudy.svg' height='45px' title='흐림'/></a>");
					} else {
						$("#weather-sky").append("<a><img alt='흐림_저녁' src='assets/images/weather/cloudy.svg' height='45px' title='흐림'/></a>");
					}
				}
			} else if (PTY == 1) { // 강수형태 비
				if (6 < Number(hours) && Number(hours) < 18) {
					$("#weather-sky").append("<a><img alt='비' src='assets/images/weather/rain.svg' height='45px' title='비'/></a>");
				} else {
					$("#weather-sky").append("<a><img alt='비_저녁' src='assets/images/weather/rain.svg' height='45px' title='비'/></a>");
				}
			} else if (PTY == 2) { // 강수형태 비/눈
				if (6 < Number(hours) && Number(hours) < 18) {
					$("#weather-sky").append("<a><img alt='비/눈' src='assets/images/weather/rain_snow.svg' height='45px' title='비/눈'/></a>");
				} else {
					$("#weather-sky").append("<a><img alt='비/눈_저녁' src='assets/images/weather/rain_snow_night.svg' height='45px' title='비/눈'/></a>");
				}
			} else if (PTY == 3) { // 강수형태 눈
				if (6 < Number(hours) && Number(hours) < 18) {
					$("#weather-sky").append("<a><img alt='눈' src='assets/images/weather/snow.svg' height='45px' title='눈'/></a>");
				} else {
					$("#weather-sky").append("<a><img alt='눈_저녁' src='assets/images/weather/snow_night.svg' height='45px' title='눈'/></a>");
				}
			} else if (PTY == 5) { // 강수형태 빗방울
				if (6 < Number(hours) && Number(hours) < 18) {
					$("#weather-sky").append("<a><img alt='빗방울' src='assets/images/weather/raindrop.svg' height='45px' title='빗방울'/></a>");
				} else {
					$("#weather-sky").append("<a><img alt='빗방울_저녁' src='assets/images/weather/raindrop.svg' height='45px' title='빗방울'/></a>");
				}
			} else if (PTY == 6) { // 강수형태 빗방울/눈날림
				if (6 < Number(hours) && Number(hours) < 18) {
					$("#weather-sky").append("<a><img alt='빗방울/눈날림' src='assets/images/weather/raindrop_blowingsnow.svg' height='45px' title='흐림'/></a>");
				} else {
					$("#weather-sky").append("<a><img alt='빗방울/눈날림_저녁' src='assets/images/weather/raindrop_blowingsnow_night.svg' height='45px' title='흐림'/></a>");
				}
			} else if (PTY == 7) { // 강수형태 눈날림
				if (6 < Number(hours) && Number(hours) < 18) {
					$("#weather-sky").append("<a><img alt='눈날림' src='assets/images/weather/cloudy.svg' height='45px' title='흐림'/></a>");
				} else {
					$("#weather-sky").append("<a><img alt='눈날림_저녁' src='assets/images/weather/cloudy.svg' height='45px' title='흐림'/></a>");
				}
			}
		},

		error: function() {
			console.log("요청 중 에러 발생");
		}
	})
}

// 위도 경도 불러오기 실패!
function error() {
	alert("Unable to retrieve your location.");
}

// LCC DFS 좌표변환 ( code : "toXY"(위경도->좌표, v1:위도, v2:경도), "toLL"(좌표->위경도,v1:x, v2:y) )
function dfs_xy_conv(code, v1, v2) {
	var DEGRAD = Math.PI / 180.0;
	var RADDEG = 180.0 / Math.PI;

	var re = RE / GRID;
	var slat1 = SLAT1 * DEGRAD;
	var slat2 = SLAT2 * DEGRAD;
	var olon = OLON * DEGRAD;
	var olat = OLAT * DEGRAD;

	var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
	var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
	var ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
	ro = re * sf / Math.pow(ro, sn);
	var rs = {};
	if (code == "toXY") {
		rs['lat'] = v1;
		rs['lng'] = v2;
		var ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
		ra = re * sf / Math.pow(ra, sn);
		var theta = v2 * DEGRAD - olon;
		if (theta > Math.PI) theta -= 2.0 * Math.PI;
		if (theta < -Math.PI) theta += 2.0 * Math.PI;
		theta *= sn;
		rs['x'] = Math.floor(ra * Math.sin(theta) + XO + 0.5);
		rs['y'] = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
	}
	else {
		rs['x'] = v1;
		rs['y'] = v2;
		var xn = v1 - XO;
		var yn = ro - v2 + YO;
		ra = Math.sqrt(xn * xn + yn * yn);
		if (sn < 0.0) - ra;
		var alat = Math.pow((re * sf / ra), (1.0 / sn));
		alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

		if (Math.abs(xn) <= 0.0) {
			theta = 0.0;
		}
		else {
			if (Math.abs(yn) <= 0.0) {
				theta = Math.PI * 0.5;
				if (xn < 0.0) - theta;
			}
			else theta = Math.atan2(xn, yn);
		}
		var alon = theta / sn + olon;
		rs['lat'] = alat * RADDEG;
		rs['lng'] = alon * RADDEG;
	}
	return rs;
}
