(function($, owner) {
	//var HOST = "http://172.19.240.64:8080";
	var HOST = "http://localhost:8080";
	var LOGIN = "/user/login";
	var SAVE_ACTIVITY = "/user/save";

	owner.save_activity = function(activityInfo, callback) {
		callback = callback || $.noop;
		activityInfo = activityInfo || {};

		console.log(HOST + SAVE_ACTIVITY);
		console.log(activityInfo.category)
		mui.ajax(HOST + SAVE_ACTIVITY, {
			type: 'post',
			timeout: 3000,
			headers: activityInfo,
			success: function(data) {
					console.dir(data)
					callback('good');
				}
			}
		);
	};
	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.account = loginInfo.account || '';
		loginInfo.password = loginInfo.password || '';
// 		if (loginInfo.account.length < 5) {
// 			return callback('账号最短为 5 个字符');
// 		}
// 		if (loginInfo.password.length < 6) {
// 			return callback('密码最短为 6 个字符');
// 		}
		
		var settings = app.getSettings();
		if (settings.autoLogin){
			localStorage.setItem("$loginInfo",JSON.stringify(loginInfo));
		}
		
		console.log(HOST + LOGIN);

		mui.ajax(HOST + LOGIN, {
			type: 'post',
			timeout: 3000,
			headers: {
				'userName': loginInfo.account,
				'password': loginInfo.password,
			},
			success: function(data) {
				result = JSON.parse(data || {});
				console.log(result);
				console.log(result.data);
				if (result.statusCode.code == 200) {
					return owner.createState(result.data, callback);
				} else {
					return callback(result.statusCode.message);
				}
			},
			error: function(xhr, type, errorThrown) {
				alert(type);
			}
		});
		// 		var users = JSON.parse(localStorage.getItem('$users') || '[]');
		// 		var authed = users.some(function(user) {
		// 			return loginInfo.account == user.account && loginInfo.password == user.password;
		// 		});
		// 		if (authed) {
		// 			return owner.createState(loginInfo.account, callback);
		// 		} else {
		// 			return callback('用户名或密码错误');
		// 		}
	};

	owner.createState = function(userInfo,loginInfo, callback) {
		var state = owner.getState();

		state.userId = userInfo.userId;
		state.userName = userInfo.userName;
		state.sex = userInfo.sex;
		state.university = userInfo.university;
				
		owner.setState(state);
		return callback();
	};

	/**
	 * 新用户注册
	 **/
	owner.reg = function(regInfo, callback) {
		callback = callback || $.noop;
		regInfo = regInfo || {};
		regInfo.account = regInfo.account || '';
		regInfo.password = regInfo.password || '';
		if (regInfo.account.length < 5) {
			return callback('用户名最短需要 5 个字符');
		}
		if (regInfo.password.length < 6) {
			return callback('密码最短需要 6 个字符');
		}
		if (!checkEmail(regInfo.email)) {
			return callback('邮箱地址不合法');
		}
		var users = JSON.parse(localStorage.getItem('$users') || '[]');
		users.push(regInfo);
		localStorage.setItem('$users', JSON.stringify(users));
		return callback();
	};

	/**
	 * 获取当前状态
	 **/
	owner.getState = function() {
		var stateText = localStorage.getItem('$state') || "{}";
		return JSON.parse(stateText);
	};

	/**
	 * 设置当前状态
	 **/
	owner.setState = function(state) {
		state = state || {};
		localStorage.setItem('$state', JSON.stringify(state));
	};

	var checkEmail = function(email) {
		email = email || '';
		return (email.length > 3 && email.indexOf('@') > -1);
	};

	/**
	 * 获取应用本地配置
	 **/
	owner.setSettings = function(settings) {
		settings = settings || {};
		localStorage.setItem('$settings', JSON.stringify(settings));
		console.log(localStorage);
	}

	/**
	 * 设置应用本地配置
	 **/
	owner.getSettings = function() {
		var settingsText = localStorage.getItem('$settings') || "{}";
		return JSON.parse(settingsText);
	}
}(mui, window.app = {}));
