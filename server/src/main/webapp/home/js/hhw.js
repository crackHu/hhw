var add_car, authorization, check_user_order_status, extend_parking_action, gen_barcode, gen_bill_info, gen_msg_loading, gen_need_pay, gen_order_info, gen_parking_detail, gen_parking_list_item, gen_user_cars, get_token, order_bill_action, parking_action, parking_add_car, place_order, query_bill_info, query_order_info, query_parking_detail, query_parking_list, query_user, request_filter, underway_order, wx_check_api, wx_config, wx_config1, wx_pay, wx_scan_code;

query_user = function(callback) {
  var params, token;
  token = get_token();
  params = {
    token: token
  };
  return $.ajax({
    type: 'GET',
    url: '/hhw/v2/user/getUserInfoByToken',
    data: params,
    dataType: 'json',
    cache: false,
    success: function(result) {
      if (result && result.data) {
        if (callback) {
          return callback(result.data);
        }
      }
    },
    error: function(e) {
      return console.log('Error Derail:', e);
    }
  });
};

query_parking_list = function() {
  var $loadingToast, msgLoading, params, token;
  token = get_token();
  msgLoading = gen_msg_loading();
  $loadingToast = $('#loadingToast');
  $loadingToast.fadeIn(100);
  params = {
    token: token
  };
  return $.ajax({
    type: 'GET',
    url: '/hhw/v2/other/otherparksite/getParkingListByPage',
    data: params,
    dataType: 'json',
    cache: false,
    success: function(result) {
      console.log(result);
      msgLoading.destroy();
      if (result && result.data) {
        return result.data.content.map(function(d, i) {
          return gen_parking_list_item(d);
        });
      } else {

      }
    },
    error: function(e) {
      msgLoading.destroy();
      return console.log('Error Derail:', e);
    }
  });
};

authorization = function() {
  var code, msgLoading, params;
  code = $.getUrlParam('code');
  msgLoading = gen_msg_loading();
  if (code) {
    params = {
      code: code
    };
    return $.ajax({
      type: 'GET',
      url: '/hhw/v2/open/wechat/getWechatUser',
      data: params,
      dataType: 'json',
      cache: false,
      success: function(result) {
        msgLoading.destroy();
        if (result && result.data) {
          $.cookie('hhw_token', result.data.token);
          return location.href = 'index.html';
        } else {
          return location.href = _authorizationLink;
        }
      },
      error: function(e) {
        msgLoading.destroy();
        return console.log('Error Derail:', e);
      }
    });
  } else {
    return location.href = _authorizationLink;
  }
};

query_parking_detail = function() {
  var msgLoading, params, pid, token;
  token = get_token();
  msgLoading = gen_msg_loading();
  pid = $.getUrlParam('pid');
  params = {
    token: token,
    id: pid
  };
  return $.ajax({
    type: 'GET',
    url: '/hhw/v2/other/otherparksite/getParkingById',
    data: params,
    dataType: 'json',
    cache: false,
    success: function(result) {
      console.log(result);
      msgLoading.destroy();
      if (result && result.data) {
        return gen_parking_detail(result.data);
      }
    },
    error: function(e) {
      msgLoading.destroy();
      return console.log('Error Derail:', e);
    }
  });
};

gen_parking_list_item = function(data) {
  var cell, cells;
  cells = $('.weui-cells');
  cell = '<a class="weui-cell weui-cell_access" href="parking.html?pid=' + data.otId + '">' + '<div class="weui-cell__hd"><img class="p_logo" src="../images/p.svg" alt=""/></div>' + '<div class="weui-cell__bd base_info">' + '<div class="title"><span>' + data.otName + '</span></div>' + '<div class="desc"><span>车位：' + data.otReleaseRes + '</span><span>¥' + data.otPrice + '/小时</span></div>' + '</div>' + '<div class="weui-cell__ft ' + (data.otFreeRes <= 1 ? 'red' : '') + '">' + '剩余：' + data.otFreeRes + '</div>' + '</a>';
  return cells.append(cell);
};

gen_parking_detail = function(data) {
  var imgPlace, parkInfo;
  imgPlace = $('.park_image');
  if (data.images.length) {
    imgPlace.append('<img src="' + data.images[0].otUrl + '" alt=""/>');
  }
  parkInfo = $('.park_info');
  parkInfo.append('<div class="weui-cell"><div class="weui-cell__bd base_info">' + '<div class="title" data-id="' + data.otId + '"><span>' + data.otName + '</span></div>' + '<div class="desc"><span>车位：' + data.otReleaseRes + '</span><span>¥' + data.otPrice + '/小时</span></div>' + '</div>' + '<div class="weui-cell__ft park_info ' + (data.otFreeRes <= 1 ? 'red' : '') + '">' + '剩余：' + data.otFreeRes + '</div>' + '</div>' + '<div class="weui-cell weui-cell_access js_item"><div class="weui-cell__hd address"><i class="icon icon-location"/></div><div class="weui-cell__bd address">' + data.otAddress + '</div><div class="weui-cell__ft"></div></div>');
  return query_user(gen_user_cars);
};

gen_user_cars = function(data) {
  var carPlace, car_selected, cars, cars_length;
  cars = data.cars;
  cars_length = cars.length;
  carPlace = $('.cars');
  cars.map(function(d) {
    return carPlace.append('<label class="weui-cell weui-check__label" for="car_' + d.otCarId + '">' + '<div class="weui-cell__bd">' + '<p class="license_plate">' + [d.otCarProvince, d.otCarCity, ' ', d.otCarNum].join('') + '</p>' + '</div>' + '<div class="weui-cell__ft">' + '<input type="radio" name="cars" class="weui-check" id="car_' + d.otCarId + '" ' + (cars_length === 1 ? 'checked' : void 0) + '>' + '<span class="weui-icon-checked"></span>' + '</input>' + '</div>' + '</label>');
  });
  if (cars_length < 5) {
    carPlace.append('<a href="javascript:void(0);" class="weui-cell weui-cell_link">' + '<div class="weui-cell__bd add_car"><i class="icon icon-add"/>添加车辆</div>' + '</a>');
  }
  if (localStorage) {
    car_selected = localStorage.car_selected;
  }
  if (car_selected) {
    return $('.license_plate').map(function() {
      if ($(this).text() === car_selected) {
        return $(this).parents('label[for^="car"]').find(':radio').attr('checked', true);
      }
    });
  }
};

get_token = function() {
  var token;
  token = $.cookie('hhw_token');
  if (token) {
    return token;
  } else {
    location.href = 'code.html';
    return null;
  }
};

gen_msg_loading = function() {
  return new $.MsgBox('努力加载中...', 2);
};

parking_action = function() {
  var $selected_car, carLen, car_id, msgLoading, params, park_site_id, place_order_success_cb, selected_car_id, token, wx_pay_success_cb;
  carLen = $('.cars label[for^="car"]').length;
  if (carLen === 0) {
    alert('请先添加车辆');
    return;
  }
  $selected_car = $('.cars :radio:checked');
  selected_car_id = $selected_car.attr('id');
  if (!selected_car_id) {
    alert('请选择车辆');
    return;
  }
  car_id = selected_car_id.replace('car_', '');
  park_site_id = $('.park_info .title').data('id');
  token = get_token();
  params = {
    'token': token,
    'carId': car_id,
    'catalog': 1,
    'parkSiteId': park_site_id
  };
  place_order_success_cb = function() {
    return msgLoading.destroy();
  };
  wx_pay_success_cb = function(res) {
    console.log('place_order_success 支付成功后的回调函数', res);
    if (localStorage) {
      localStorage.car_selected = $selected_car.parents('label[for^="car"]').find('.license_plate').text();
    }
    return location.href = 'order.html';
  };
  msgLoading = gen_msg_loading();
  return place_order(params, place_order_success_cb, wx_pay_success_cb);
};

order_bill_action = function() {
  var $detail, car_id, msgLoading, need_pay, order_id, params, park_site_id, place_order_success_cb, token, wx_pay_success_cb;
  msgLoading = gen_msg_loading();
  token = get_token();
  $detail = $('.order .detail');
  order_id = $detail.data('orderId');
  car_id = $detail.data('carId');
  park_site_id = $detail.data('parkSiteId');
  need_pay = $detail.data('needPay');
  if (need_pay === 0) {
    location.href = 'bill.html';
  }
  params = {
    "orderId": order_id,
    "price": need_pay = 0.01,
    'token': token,
    'carId': car_id,
    'catalog': 1,
    'parkSiteId': park_site_id
  };
  place_order_success_cb = function() {
    return msgLoading.destroy();
  };
  wx_pay_success_cb = function(res) {
    console.log('order_bill_success 支付成功后的回调函数', res);
    return location.href = 'bill.html';
  };
  return place_order(params, place_order_success_cb, wx_pay_success_cb);
};

parking_add_car = function() {
  var config, hide_keyboard;
  $('.pn_wapper').removeClass('s2');
  $('.pn_wapper').removeClass('hide');
  hide_keyboard = function() {
    return $('.pn_wapper').addClass('hide');
  };
  config = {
    title: '车牌号',
    content: '<span class="license_plate_input"></span>',
    auxiliary: {
      text: '取消',
      callback: hide_keyboard
    },
    main: {
      text: '添加',
      callback: function() {
        var callback, city, license_plate, number, province;
        license_plate = $('.license_plate_input').text();
        if (license_plate.length === 8) {
          province = license_plate.substring(0, 1);
          city = license_plate.substring(1, 2);
          number = license_plate.substring(3, 8);
          callback = function() {
            if (localStorage) {
              return localStorage.car_selected = license_plate;
            }
          };
          add_car(province, city, number, callback);
        } else {
          alert('输入车牌号有误');
          return false;
        }
        return hide_keyboard();
      }
    }
  };
  return new $.Dialog(config);
};

add_car = function(province, city, number, callback) {
  var msgLoading, params, token;
  msgLoading = gen_msg_loading();
  token = get_token();
  params = {
    otCarProvince: province,
    otCarCity: city,
    otCarNum: number,
    token: token
  };
  return $.ajax({
    url: '/hhw/v2/other/car/setCarByToken',
    data: params,
    cache: false,
    success: function(result) {
      msgLoading.destroy();
      $('.cars').empty();
      return request_filter(result, function() {
        if (callback) {
          callback();
        }
        return query_user(gen_user_cars);
      });
    },
    error: function(e) {
      msgLoading.destroy();
      return console.log('Error Derail:', e);
    }
  });
};

place_order = function(params, po_success_cb, wp_success_cb) {
  return $.ajax({
    url: '/hhw/v2/order/placeOrder',
    data: params,
    cache: false,
    success: function(result) {
      var code, data, message;
      po_success_cb();
      console.log(result);
      code = result.code;
      message = result.message;
      if (code) {
        if (code === '1001') {
          data = result.data;
          wx_config(data);
          return wx_pay(data, wp_success_cb);
        } else {
          return alert(message);
        }
      }
    },
    error: function(e) {
      msgLoading.destroy();
      return console.log('Error Derail:', e);
    }
  });
};

wx_config = function(data) {
  return wx.config({
    debug: false,
    appId: data.appId,
    timestamp: data.timeStamp,
    nonceStr: data.nonceStr,
    signature: data.signType,
    jsApiList: ['chooseWXPay']
  });
};

wx_config1 = function() {
  return wx.config({
    debug: true,
    appId: 'wx219c336fa191f482',
    timestamp: Date.now(),
    nonceStr: 'http:/dev.basoft.cn/hhw/v2/open/pay/callBack',
    signature: '<xml><appid>wx219c336fa191f482</appid><body>hhw-停车</body><mch_id>1419627002</mch_id><nonce_str>http:/dev.basoft.cn/hhw/v2/open/pay/callBack</nonce_str><out_trade_no>20170207105728713908</out_trade_no><time_expire>20170208105728</time_expire><time_start>20170207105728</time_start><total_fee>800</total_fee><trade_type>JSAPI</trade_type><sign>78C8CC5C49F3E1A28109BCC42F2DE31A</sign></xml>',
    jsApiList: ['chooseWXPay', 'scanQRCode']
  });
};

wx_pay = function(data, success_cb) {
  return wx.chooseWXPay({
    timestamp: data.timeStamp,
    nonceStr: data.nonceStr,
    "package": data["package"],
    signType: data.signType,
    paySign: data.paySign,
    success: function() {
      alert('支付成功');
      return success_cb();
    }
  });
};

wx_check_api = function(api) {
  return wx.checkJsApi({
    jsApiList: api,
    success: function(res) {
      return console.log('wx_check_api', res);
    }
  });
};

wx_scan_code = function() {
  wx_config1();
  wx_check_api(['scanQRCode']);
  return wx.scanQRCode({
    needResult: 0,
    scanType: ["qrCode", "barCode"],
    success: function(res) {
      var result;
      result = res.resultStr;
      console.log('res', res);
      return console.log('result', result);
    }
  });
};

check_user_order_status = function() {
  var checked_callback, msgLoading, params, token;
  checked_callback = function(data) {
    var status;
    if (data) {
      status = data.odStatus;
      if (status === 1) {
        location.href = 'order.html';
      }
      if (status === 2) {
        return location.href = 'bill.html';
      }
    }
  };
  msgLoading = gen_msg_loading();
  token = get_token();
  params = {
    token: token
  };
  return $.ajax({
    url: '/hhw/v2/order/hasOrder',
    data: params,
    dataType: 'json',
    cache: false,
    success: function(result) {
      msgLoading.destroy();
      return request_filter(result, checked_callback);
    },
    error: function(e) {
      msgLoading.destroy();
      return console.log('Error Derail:', e);
    }
  });
};

query_order_info = function() {
  return underway_order(true, 1, gen_order_info);
};

gen_order_info = function(order) {
  var $detail, $order, $park, end_time, leave_time, need_pay, park, unit_price;
  if (!order) {
    alert('未查询到相关信息');
    history.go(-1);
    return;
  }
  park = order.parkSite;
  console.log(order, park);
  $order = $('.order');
  $park = $order.find('.park_info');
  $detail = $order.find('.detail');
  $park.find('.base_info .name span').text(park.otName);
  $park.find('.js_item .address').text(park.otAddress);
  end_time = order.odEndTime;
  leave_time = end_time + (15 * 60 * 1000);
  unit_price = park.otPrice;
  need_pay = gen_need_pay(end_time, unit_price);
  $detail.find('.license_plate').text(order.odCarNum);
  $detail.find('.price').text('¥ ' + $.toDecimal2(unit_price));
  $detail.find('.start_time').text($.formatTime(new Date(order.odStartTime)));
  $detail.find('.end_time').text($.formatTime(new Date(end_time)));
  $detail.find('.need_pay').text('¥ ' + $.toDecimal2(need_pay));
  $detail.data('orderId', order.odId);
  $detail.data('carId', order.odCarId);
  $detail.data('parkSiteId', order.odParkSiteId);
  $detail.data('needPay', need_pay);
  return gen_barcode(order.odBarcode);
};

underway_order = function(status_known, status, callback) {
  var msgLoading, params, token;
  if (status_known == null) {
    status_known = false;
  }
  if (status == null) {
    status = 1;
  }
  msgLoading = gen_msg_loading();
  token = get_token();
  params = {
    token: token
  };
  return $.ajax({
    url: '/hhw/v2/order/underwayOrder',
    data: params,
    dataType: 'json',
    cache: false,
    success: function(result) {
      var cb;
      msgLoading.destroy();
      cb = function(ret) {
        if (status_known) {
          if (!ret || status !== ret.odStatus) {
            alert('未查询到相关信息');
            return history.go(-1);
          } else {
            return callback(ret);
          }
        } else {
          return callback(ret);
        }
      };
      return request_filter(result, cb);
    },
    error: function(e) {
      msgLoading.destroy();
      return console.log('Error Derail:', e);
    }
  });
};

query_bill_info = function() {
  return underway_order(true, 2, gen_bill_info);
};

gen_bill_info = function(bill) {
  var $bill, $bill_detail, $bill_info, $park, $park_time, end_time, first_price, leave_time, need_pay, park, park_time, second_price, start_time, total_price, unit_price;
  if (!bill) {
    alert('未查询到相关信息');
    history.go(-1);
    return;
  }
  park = bill.parkSite;
  $bill = $('.bill');
  $park = $bill.find('.park_info');
  $bill_info = $bill.find('.bill_info');
  $bill_detail = $bill.find('.bill_detail');
  $park.find('.park_name').text(park.otName);
  $park.find('.license_plate').text(bill.odCarNum);
  total_price = bill.odAddPrice;
  first_price = bill.odFirstPrice;
  second_price = total_price - first_price;
  start_time = bill.odStartTime;
  end_time = bill.odEndTime;
  leave_time = end_time + (15 * 60 * 1000);
  park_time = end_time - start_time;
  unit_price = park.otPrice;
  need_pay = gen_need_pay(end_time, unit_price);
  console.log('need_pay', need_pay);
  if (need_pay === 0) {
    $('.bill .action .weui-btn').addClass('weui-btn_disabled');
  }
  $bill_info.find('.total_price').text('¥ ' + $.toDecimal2(total_price));
  $bill_info.find('.first_price').text('¥ ' + $.toDecimal2(first_price));
  $bill_info.find('.leave_time').text($.formatTime(new Date(leave_time)));
  $bill_detail.find('.start_time').text($.formatTime(new Date(start_time)));
  $park_time = $bill_detail.find('.park_time');
  $park_time.text($.timediff(park_time));
  $bill_detail.find('.first_price').text('¥ ' + $.toDecimal2(first_price));
  $bill_detail.find('.add_price').text('¥ ' + $.toDecimal2(second_price));
  $bill.data('orderId', bill.odId);
  $bill.data('carId', bill.odCarId);
  $bill.data('parkSiteId', bill.odParkSiteId);
  $bill.data('needPay', need_pay);
  return gen_barcode(bill.odBarcode);
};

extend_parking_action = function() {
  var $bill, car_id, msgLoading, need_pay, order_id, params, park_site_id, place_order_success_cb, token, wx_pay_success_cb;
  $bill = $('.bill');
  need_pay = $bill.data('needPay');
  if (need_pay === 0) {
    return;
  }
  msgLoading = gen_msg_loading();
  token = get_token();
  order_id = $bill.data('orderId');
  car_id = $bill.data('carId');
  park_site_id = $bill.data('parkSiteId');
  params = {
    "orderId": order_id,
    "price": need_pay = 0.01,
    'token': token,
    'carId': car_id,
    'catalog': 1,
    'parkSiteId': park_site_id
  };
  place_order_success_cb = function() {
    return msgLoading.destroy();
  };
  wx_pay_success_cb = function(res) {
    console.log('extend_parking_action 支付成功后的回调函数', res);
    return location.reload();
  };
  return place_order(params, place_order_success_cb, wx_pay_success_cb);
};

request_filter = function(result, callback) {
  var code, message, ret;
  console.log(result);
  code = result.code;
  message = result.message;
  if (code) {
    if (code === '1001') {
      ret = result.data;
      return callback(ret);
    } else {
      return alert(message);
    }
  } else {
    return alert('Internal error [code undefined]');
  }
};

gen_barcode = function(content) {
  return JsBarcode("#barcode", content, {
    format: "code39",
    margin: 1,
    fontSize: 20,
    height: 150
  });
};

gen_need_pay = function(end_time, unit_price) {
  var apart, apart_hour, arr, e, half_hour, hour, keep_decimal, need_pay, now_time;
  need_pay = 999;
  try {
    need_pay = 0;
    now_time = Date.now();
    apart = end_time - now_time;
    if (apart > 0 || apart === 0) {
      return 0;
    }
    apart_hour = Math.abs(apart) / (1000 * 60 * 60);
    keep_decimal = parseFloat(apart_hour).toFixed(1);
    arr = String(keep_decimal).split('.');
    hour = arr[0];
    half_hour = arr[1];
    need_pay = hour * unit_price;
    if (half_hour > 5) {
      need_pay += unit_price;
    } else if (half_hour !== 0) {
      need_pay += 0.5 * unit_price;
    }
  } catch (_error) {
    e = _error;
    console.log('Error Derail:', e);
  }
  return need_pay;
};

$(document).on('touchstart', 'dd.key', function(e) {
  var me;
  e.preventDefault();
  me = $(this);
  if (!me.hasClass('disable')) {
    return me.addClass('active');
  }
});

console.log('localStorage :', localStorage);

$(document).on('touchend', 'dd.key.delete_key', function(e) {
  var $input, after_len, before_len, input_after, input_before, spacing;
  e.preventDefault();
  $input = $('.license_plate_input');
  input_before = $input.text();
  before_len = input_before.length;
  spacing = input_before.endsWith(' ');
  if (spacing) {
    input_after = input_before.substring(0, before_len - 2);
  } else {
    input_after = input_before.substring(0, before_len - 1);
  }
  after_len = input_after.length;
  if (after_len === 0) {
    $('.pn_wapper').removeClass('s2');
  }
  return $input.text(input_after);
});

$(document).on('touchend', 'dd.key', function(e) {
  var $input, after_len, before_len, input_after, input_before, last_key, letter_key, me, num_key, val;
  e.preventDefault();
  me = $(this);
  $input = $('.license_plate_input');
  if (!me.hasClass('disable')) {
    me.removeClass('active');
    val = me.attr('value');
    num_key = $('.pn_wapper .pn_2 dd').filter('.num_key');
    letter_key = $('.pn_wapper .pn_2 dd').filter('.letter_key');
    last_key = $('.pn_wapper .pn_2 dd').filter('.last_key');
    input_before = $input.text();
    before_len = input_before.length;
    if (before_len < 8) {
      $input.append(val);
      input_after = $input.text();
      after_len = input_after.length;
      last_key.addClass('disable');
      if (after_len === 1) {
        $('.pn_wapper').addClass('s2');
        num_key.addClass('disable');
      }
      if (after_len > 1) {
        num_key.removeClass('disable');
      }
      if (after_len === 2) {
        $input.append(' ');
      }
      if (after_len === 7) {
        return last_key.removeClass('disable');
      }
    }
  }
});

var _authorizationLink;

_authorizationLink = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx219c336fa191f482&redirect_uri=http%3A%2F%2Fdev.basoft.cn%2Fhhw%2Fhome%2Fcode.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect';

$.MsgBox = function(message, type, lifetime) {
  var $element, self;
  if (type == null) {
    type = 1;
  }
  if (lifetime == null) {
    lifetime = 0;
  }
  self = this;
  $element = $('.msgBox_' + type);
  self.init = function() {
    var msgBox;
    if ($('.msgBox_' + type).length === 0) {
      msgBox = '<div class="msgBox_' + type + '" style="display: none">' + '<div class="mask_transparent"></div><div class="weui-toast">' + '<i class="weui-icon_toast ' + (type === 2 ? 'weui-loading' : 'weui-icon-success-no-circle') + '"/>' + '<p class="weui-toast__content">' + message + '</p>' + '</div>' + '</div>';
      $('body').append(msgBox);
      return $element = $('.msgBox_' + type);
    }
  };
  self.show = function() {
    return $element.fadeIn(100);
  };
  self.hide = function() {
    return $element.fadeOut(100);
  };
  self.destroy = function() {
    $element.fadeOut(100);
    return setTimeout((function() {
      return $element.remove();
    }), 200);
  };
  self.init(message, type);
  self.show();
  if (lifetime > 0) {
    return setTimeout((function() {
      self.hide();
      return self.destroy();
    }), lifetime);
  }
};

$.Dialog = function(config, type) {
  var $element, self;
  if (type == null) {
    type = 1;
  }
  self = this;
  $element = $('.dialog_' + type);
  self.init = function() {
    var auxiliary, dialog, main;
    auxiliary = config.auxiliary || {};
    main = config.main || {};
    if ($('.dialog_' + type).length === 0) {
      dialog = '<div class="dialog_' + type + '" style="display: none;">' + '<div class="weui-mask"></div>' + '<div class="weui-dialog" style="position: fixed; top:20%">' + '<div class="weui-dialog__hd"><strong class="weui-dialog__title">' + config.title + '</strong></div>' + '<div class="weui-dialog__bd">' + config.content + '</div>' + '<div class="weui-dialog__ft">' + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default dialog_auxiliary">' + auxiliary.text + '</a>' + '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary dialog_main">' + main.text + '</a>' + '</div>' + '</div>' + '</div>';
      $('body').append(dialog);
      $element = $('.dialog_' + type);
      $(document).on('touchend', '.dialog_auxiliary', function() {
        var is_close;
        is_close = true;
        if (auxiliary.callback) {
          is_close = auxiliary.callback();
        }
        if (is_close) {
          return self.destroy();
        }
      });
      return $(document).on('touchend', '.dialog_main', function() {
        var is_close;
        is_close = true;
        if (main.callback) {
          is_close = main.callback();
        }
        if (is_close) {
          return self.destroy();
        }
      });
    }
  };
  self.show = function() {
    return $element.fadeIn(100);
  };
  self.hide = function() {
    return $element.fadeOut(100);
  };
  self.destroy = function() {
    $(document).off('touchend', '.dialog_auxiliary');
    $(document).off('touchend', '.dialog_main');
    $element.fadeOut(100);
    return setTimeout((function() {
      return $element.remove();
    }), 200);
  };
  self.init(type, config);
  return self.show();
};

$.getUrlParam = function(name) {
  var r, reg;
  reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
  r = window.location.search.substr(1).match(reg);
  if (r !== null) {
    return unescape(r[2]);
  }
  return null;
};

$(document).on('touchend', 'div.back_button', function(e) {
  e.preventDefault();
  return history.go(-1);
});

$.formatTime = function(date, sp1, sp2) {
  var day, formatNumber, hour, minute, month, second, year;
  if (sp1 == null) {
    sp1 = '-';
  }
  if (sp2 == null) {
    sp2 = ':';
  }
  year = date.getFullYear();
  month = date.getMonth() + 1;
  day = date.getDate();
  hour = date.getHours();
  minute = date.getMinutes();
  second = date.getSeconds();
  formatNumber = function(n) {
    n = n.toString();
    if (n[1]) {
      return n;
    } else {
      return '0' + n;
    }
  };
  return [year, month, day].map(formatNumber).join(sp1) + ' ' + [hour, minute].map(formatNumber).join(sp2);
};

$.toDecimal2 = function(x) {
  var f, rs, s;
  f = parseFloat(x);
  if (isNaN(f)) {
    return false;
  }
  f = Math.round(x * 100) / 100;
  s = f.toString();
  rs = s.indexOf('.');
  if (rs < 0) {
    rs = s.length;
    s += '.';
  }
  while (s.length <= rs + 2) {
    s += '0';
  }
  return s;
};

$.timediff = function(apart_ts) {
  var d_days, d_hours, d_minutes, d_seconds, fillZero, ret;
  ret = "";
  d_seconds = apart_ts / 1000;
  if (d_seconds < 0) {
    return -1;
  }
  fillZero = function(i) {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  };
  d_days = parseInt(d_seconds / 86400);
  d_hours = parseInt(d_seconds / 3600) - d_days * 24;
  d_minutes = parseInt(d_seconds / 60) - d_days * 24 * 60 - d_hours * 60;
  d_seconds = d_seconds - d_days * 24 * 60 * 60 - d_hours * 60 * 60 - d_minutes * 60;
  if (d_days > 0) {
    ret += fillZero(d_days) + "天 ";
  }
  if (d_hours > 0) {
    ret += fillZero(d_hours) + ":";
  } else {
    ret += "00:";
  }
  if (d_minutes > 0) {
    ret += fillZero(d_minutes) + ":";
  } else {
    ret += "00:";
  }
  if (d_seconds > 0) {
    ret += fillZero(d_seconds);
  } else {
    ret += "00";
  }
  return ret;
};
