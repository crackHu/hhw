query_user = (callback) ->
  token = get_token()
  params = {
    token: token
  }
  $.ajax
    type: 'GET'
    url: '/hhw/v2/user/getUserInfoByToken'
    data: params,
    dataType: 'json'
    cache: false
    success: (result) ->
      if result and result.data
        if callback
          callback(result.data)
    error: (e) ->
      console.log('Error Derail:', e)

query_parking_list = () ->
  token = get_token()
  msgLoading = gen_msg_loading()
  $loadingToast = $('#loadingToast')
  $loadingToast.fadeIn(100);
  params = {
    token: token
  }
  $.ajax
    type: 'GET'
    url: '/hhw/v2/other/otherparksite/getParkingListByPage'
    data: params,
    dataType: 'json'
    cache: false
    success: (result) ->
      console.log result
      msgLoading.destroy()
      if result and result.data
        result.data.content.map (d, i) ->
          gen_parking_list_item(d)
      else
    error: (e) ->
      msgLoading.destroy()
      console.log('Error Derail:', e)

authorization = () ->
  code = $.getUrlParam('code')
  msgLoading = gen_msg_loading()
  if code
    params = {
      code: code
    }
    $.ajax
      type: 'GET'
      url: '/hhw/v2/open/wechat/getWechatUser'
      data: params,
      dataType: 'json'
      cache: false
      success: (result) ->
        msgLoading.destroy()
        if result and result.data
          $.cookie('hhw_token', result.data.token)
          location.href = 'index.html'
        else
          location.href = _authorizationLink
      error: (e) ->
        msgLoading.destroy()
        console.log('Error Derail:', e)
  else
    location.href = _authorizationLink

query_parking_detail = () ->
  token = get_token()
  msgLoading = gen_msg_loading()
  pid = $.getUrlParam('pid')
  params = {
    token: token,
    id: pid
  }
  $.ajax
    type: 'GET'
    url: '/hhw/v2/other/otherparksite/getParkingById'
    data: params,
    dataType: 'json'
    cache: false
    success: (result) ->
      console.log result
      msgLoading.destroy()
      if result and result.data
        gen_parking_detail(result.data)
    error: (e) ->
      msgLoading.destroy()
      console.log('Error Derail:', e)

gen_parking_list_item = (data) ->
  cells = $('.container')
  cell = '<a class="weui-cell weui-cell_access" href="parking.html?pid=' + data.otId + '">' +
    '<div class="weui-cell__hd"><img class="p_logo" src="../images/p.svg" alt=""/></div>' +
    '<div class="weui-cell__bd base_info">' +
    '<div class="title"><span>' + data.otName + '</span></div>' +
    '<div class="desc"><span>车位：' + data.otReleaseRes + '</span><span>¥' + data.otPrice + '/小时</span></div>' +
    '</div>' +
    '<div class="weui-cell__ft ' + (if data.otFreeRes <= 1 then 'red' else '') + '">' +
    '剩余：' + data.otFreeRes +
    '</div>' +
    '</a>'

  cells.append(cell)

gen_parking_detail = (data) ->
  imgPlace = $('.park_image')
  if data.images.length
    imgPlace.append(
      '<img src="' + data.images[0].otUrl + '" alt=""/>'
    )

  parkInfo = $('.park_info')
  parkInfo.append(
    '<div class="weui-cell"><div class="weui-cell__bd base_info">' +
      '<div class="title" data-id="' + data.otId + '"><span>' + data.otName + '</span></div>' +
      '<div class="desc"><span>车位：' + data.otReleaseRes + '</span><span>¥' + data.otPrice + '/小时</span></div>' +
      '</div>' +
      '<div class="weui-cell__ft park_info ' + (if data.otFreeRes <= 1 then 'red' else '') + '">' +
      '剩余：' + data.otFreeRes +
      '</div>' +
      '</div>' +
      '<div class="weui-cell weui-cell_access js_item"><div class="weui-cell__hd address data-lng data-lat"><i class="icon icon-location"/></div><div class="weui-cell__bd address">' + data.otAddress + '</div><div class="weui-cell__ft"></div></div>'
  )
  parkAddress = parkInfo.find('.address')
  parkAddress.data('lng', data.otPointX)
  parkAddress.data('lat', data.otPointY)

  query_user(gen_user_cars)

gen_map = () ->
  map = '<div class="map_wapper" style="display: none">' + 
          '<div class="map_close">X</div>' + 
          '<div class="map_container">' +
            '<div id="allmap"></div>' + 
          '</div>' + 
        '</div>'
  $('.wapper').append(map)

  $('.map_wapper').fadeIn();
  $('.back_button').hide();
  map = new BMap.Map("allmap");
  point = new BMap.Point(113.326513, 23.130774);
  top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
  top_left_navigation = new BMap.NavigationControl();
  map.centerAndZoom(point, 17);
  #加入缩放
  map.addControl(top_left_control);
  map.addControl(top_left_navigation);
  map.addEventListener("click", (e) ->
      console.log("Click:", e.point.lng, e.point.lat);
  )

  json_data = [
    {
      'point': [
        113.325939
        23.129881
      ]
      'title': '妇女儿童医疗中心停车场'
    }
    {
      'point': [
        113.327016
        23.131738
      ]
      'title': '津滨腾越停车场'
    }
  ]

  pointArray = new Array
  # i = 0
  # while i < json_data.length
  #   xx = json_data[i].point[0]
  #   yy = json_data[i].point[1]
  #   marker = new (BMap.Marker)(new (BMap.Point)(xx, yy))
  #   # 创建点
  #   tempPoint = new (BMap.Point)(xx, yy)
  #   marker.setAnimation BMAP_ANIMATION_BOUNCE
  #   opts = 
  #     position: tempPoint
  #     offset: new (BMap.Size)(10, -30)
  #   label = new (BMap.Label)(json_data[i].title, opts)
  #   label.setStyle
  #     color: 'black'
  #     fontsize: '14px'
  #     height: '20px'
  #     lineHeight: '20px'
  #     fontFamily: '微软雅黑'
  #   map.addOverlay marker
  #   #增加点
  #   map.addOverlay label
  #   #增加标签
  #   pointArray[i] = new (BMap.Point)(json_data[i][0], json_data[i][1])
  #   marker.addEventListener 'click', attribute
  #   i++
  #   #让所有点在视野范围内,中心定位妇幼
  #   #    map.setViewport(pointArray);
  #   #加入缩放
  #   map.addControl top_left_control
  #   map.addControl top_left_navigation
  #   #单击获取点击的经纬度
  #   map.addEventListener 'click', (e) ->
  #     console.log 'Click:', e.point.lng, e.point.lat
  #     return
  #p1 = null #new (BMap.Point)(113.325939, 23.129881)
  #妇幼的坐标
  geolocation = new (BMap.Geolocation)
  geolocation.getCurrentPosition ((r) ->
    lng = $('.address').data 'lng'
    lat =$('.address').data 'lat'
    console.log 'address', lng, lat
    # p2 = new (BMap.Point)(113.327016, 23.131738)
    p2 = new (BMap.Point)(lng, lat)
    if @getStatus() == BMAP_STATUS_SUCCESS
      p1 = new (BMap.Point)(r.point.lng, r.point.lat)
      driving = new (BMap.DrivingRoute)(map, renderOptions:
        map: map
        autoViewport: true)
      driving.search p1, p2
  ), enableHighAccuracy: true

  setTop = () ->
    mapTop = $(".map_wapper").height() - 18;
    $('.map_container').height(mapTop);
    console.log('setTop');
  setTop();
  console.log('map end');

gen_user_cars = (data) ->
  cars = data.cars
  cars_length = cars.length
  carPlace = $('.cars')
  cars.map (d) ->
    carPlace.append(
      '<label class="weui-cell weui-check__label" for="car_' + d.otCarId + '">' +
        '<div class="weui-cell__bd">' +
          '<p class="license_plate">' + [d.otCarProvince, d.otCarCity, ' ', d.otCarNum].join('') + '</p>' +
        '</div>' +
        '<div class="weui-cell__ft">' +
          '<input type="radio" name="cars" class="weui-check" id="car_' + d.otCarId + '" ' + ('checked' if cars_length is 1) + '>' +
            '<span class="weui-icon-checked"></span>' +
          '</input>' +
        '</div>' +
      '</label>'
    )
  if cars_length < 5
    carPlace.append(
      '<a href="javascript:void(0);" class="weui-cell weui-cell_link">' +
        '<div class="weui-cell__bd add_car"><i class="icon icon-add"/>添加车辆</div>' +
      '</a>'
    )
  # 添加默认车辆选择
  car_selected = localStorage.car_selected if localStorage
  if car_selected
    $('.license_plate').map ->
      if $(this).text() is car_selected
        $(this).parents('label[for^="car"]').find(':radio').attr('checked', true)

get_token = () ->
  token = $.cookie('hhw_token')
  if token
    return token
  else
    location.href = 'code.html'
    return null

gen_msg_loading = () ->
  return new ($.MsgBox)('努力加载中...', 2)

# 立即 Hold 住车位
parking_action = () ->
    carLen = $('.cars label[for^="car"]').length
    if carLen is 0
      alert '请先添加车辆'
      return
    $selected_car = $('.cars :radio:checked')
    selected_car_id = $selected_car.attr('id')
    if not selected_car_id
      alert '请选择车辆'
      return
    car_id = selected_car_id.replace('car_', '')
    park_site_id = $('.park_info .title').data('id')
    token = get_token()
    params = {
      'token': token,
      'carId': car_id,
      'catalog': 1,
      'parkSiteId': park_site_id,
    }
    place_order_success_cb = () ->
      msgLoading.destroy()
    wx_pay_success_cb = (res) ->
      console.log('place_order_success 支付成功后的回调函数', res)
      localStorage.car_selected = $selected_car.parents('label[for^="car"]').find('.license_plate').text() if localStorage
      location.href = 'order.html'
    msgLoading = gen_msg_loading()
    place_order true, params, place_order_success_cb, wx_pay_success_cb

# 结算出场
order_bill_action = () ->
    msgLoading = gen_msg_loading()
    token = get_token()

    $detail = $('.order .detail')
    order_id = $detail.data('orderId')
    car_id = $detail.data('carId')
    park_site_id = $detail.data('parkSiteId')
    need_pay = $detail.data('needPay')
    params = {
      "orderId": order_id,
      "price": 0.01,
      'token': token,
      'carId': car_id,
      'catalog': 1,
      'parkSiteId': park_site_id,
    }

    place_order_success_cb = () ->
      msgLoading.destroy()
      location.href = 'bill.html' if need_pay is 0
    wx_pay_success_cb = (res) ->
      console.log('order_bill_success 支付成功后的回调函数', res)
      location.href = 'bill.html'
    place_order need_pay isnt 0, params, place_order_success_cb, wx_pay_success_cb

# 停车页面添加车辆
parking_add_car = () ->
  $('.pn_wapper').removeClass('s2')
  $('.pn_wapper').removeClass('hide')

  hide_keyboard = ->
    $('.pn_wapper').addClass('hide')
    
  config = 
    title: '车牌号',
    content: '<span class="license_plate_input"></span>',
    auxiliary:
      text: '取消',
      callback: hide_keyboard,
    main:
      text: '添加',
      callback: ->
        license_plate = $('.license_plate_input').text()
        if license_plate.length is 8
          province = license_plate.substring 0, 1
          city = license_plate.substring 1, 2
          number = license_plate.substring 3, 8

          callback = ->
            localStorage.car_selected = license_plate if localStorage
          add_car(province, city, number, callback)
        else
          alert '输入车牌号有误'
          return false
        hide_keyboard()

  new($.Dialog)(config)

add_car = (province, city, number, callback) ->
  msgLoading = gen_msg_loading()
  token = get_token()
  params = {
    otCarProvince: province,
    otCarCity: city,
    otCarNum: number,
    token
  }
  $.ajax
    url: '/hhw/v2/other/car/setCarByToken',
    data: params,
    cache: false,
    success: (result) ->
      msgLoading.destroy()
      $('.cars').empty()
      request_filter(result, ->
        callback() if callback
        query_user(gen_user_cars)
      )
    error: (e) ->
      msgLoading.destroy()
      console.log('Error Derail:', e)

# 下订单
# po_success_cb 下订单成功后的回调函数
# wp_success_cb 支付成功后的回调函数
place_order = (need_pay = true, params, po_success_cb, wp_success_cb) ->
  $.ajax
      url: '/hhw/v2/order/placeOrder',
      data: params,
      cache: false,
      success: (result) ->
        po_success_cb()
        console.log result
        code = result.code
        message = result.message
        if code
          if code == '1001' 
            data = result.data
            if need_pay
              wx_config data
              wx_pay data, wp_success_cb
          else
            alert message
      error: (e) ->
        msgLoading.destroy()
        console.log('Error Derail:', e)

# 微信config
wx_config = (data) ->
  wx.config
    debug: false, # 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: data.appId, # 必填，公众号的唯一标识
    timestamp: data.timeStamp, # 必填，生成签名的时间戳
    nonceStr: data.nonceStr, # 必填，生成签名的随机串
    signature: data.signType,# 必填，签名，见附录1
    jsApiList: ['chooseWXPay'] # 必填，需要使用的JS接口列表，所有JS接口列表见附录2

# 微信config
wx_config1 = () ->
  wx.config
    debug: true, # 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wx219c336fa191f482', # 必填，公众号的唯一标识
    timestamp: Date.now(), # 必填，生成签名的时间戳
    nonceStr: 'http:/dev.basoft.cn/hhw/v2/open/pay/callBack', # 必填，生成签名的随机串
    signature: '<xml><appid>wx219c336fa191f482</appid><body>hhw-停车</body><mch_id>1419627002</mch_id><nonce_str>http:/dev.basoft.cn/hhw/v2/open/pay/callBack</nonce_str><out_trade_no>20170207105728713908</out_trade_no><time_expire>20170208105728</time_expire><time_start>20170207105728</time_start><total_fee>800</total_fee><trade_type>JSAPI</trade_type><sign>78C8CC5C49F3E1A28109BCC42F2DE31A</sign></xml>',# 必填，签名，见附录1
    jsApiList: ['chooseWXPay', 'scanQRCode'] # 必填，需要使用的JS接口列表，所有JS接口列表见附录2

# 微信支付
wx_pay = (data, success_cb) ->
  wx.chooseWXPay
    timestamp: data.timeStamp, # 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
    nonceStr: data.nonceStr, # 支付签名随机串，不长于 32 位
    package: data.package, # 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
    signType: data.signType, # 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
    paySign: data.paySign, # 支付签名
    success: ->
      alert('支付成功')
      success_cb()

# 检测微信api
wx_check_api = (api) ->
    wx.checkJsApi
      jsApiList: api, # 需要检测的JS接口列表，所有JS接口列表见附录2,
      success: (res) ->
        # 以键值对的形式返回，可用的api值true，不可用为false
        # 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
        console.log 'wx_check_api', res

# 扫一扫
wx_scan_code = () ->
  wx_config1()
  wx_check_api(['scanQRCode'])
  wx.scanQRCode
    needResult: 0, # 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
    scanType: ["qrCode","barCode"], # 可以指定扫二维码还是一维码，默认二者都有
    success: (res) ->
      result = res.resultStr; # 当needResult 为 1 时，扫码返回的结果
      console.log 'res', res
      console.log 'result', result

# 检查用户订单状态
check_user_order_status = ->
  existed = false

  checked_callback = (data) ->
    if data
      status = data.odStatus
      if status is 1
        existed = true
        if data.odAddPrice is data.odFirstPrice
          location.href = 'order.html'
        else
          location.href = 'bill.html'
      if status is 2
        existed = true
        location.href = 'bill.html'

  msgLoading = gen_msg_loading()
  token = get_token()
  params = {
    token: token,
  }
  $.ajax
    url: '/hhw/v2/order/hasOrder'
    data: params,
    dataType: 'json'
    cache: false
    success: (result) ->
      msgLoading.destroy()
      request_filter(result, checked_callback)
    error: (e) ->
      msgLoading.destroy()
      console.log('Error Derail:', e)
  return existed

# 查询订单数据
query_order_info = () ->
  underway_order(gen_order_info)
  # msgLoading = gen_msg_loading()
  # token = get_token()
  # params = {
  #   token: token,
  #   status: 1,
  # }
  # $.ajax
  #   url: '/hhw/v2/order/getOrderListByToken'
  #   data: params,
  #   dataType: 'json'
  #   cache: false
  #   success: (result) ->
  #     msgLoading.destroy()
  #     request_filter(result, gen_order_info)
  #   error: (e) ->
  #     msgLoading.destroy()
  #     console.log('Error Derail:', e)

# 生成订单页面数据
gen_order_info = (order) ->
  if !order
    alert('未查询到相关信息')
    history.go -1
    return
  park = order.parkSite

  console.log order, park

  $order = $('.order')
  $park = $order.find('.park_info')
  $detail = $order.find('.detail')

  $park.find('.base_info .name span').text(park.otName)
  $park.find('.js_item .address').text(park.otAddress)

  end_time = order.odEndTime
  # 离场时间的结束时间 +15min
  leave_time = end_time + (15 * 60 * 1000)
  leave_time = end_time
  unit_price = park.otPrice
  need_pay = gen_need_pay(end_time, unit_price)

  $detail.find('.license_plate').text(order.odCarNum)
  $detail.find('.price').text('¥ ' + $.toDecimal2(unit_price))
  $detail.find('.start_time').text($.formatTime(new Date(order.odStartTime)))
  $detail.find('.end_time').text($.formatTime(new Date(end_time)))
  $detail.find('.need_pay').text('¥ ' + $.toDecimal2(need_pay))

  $detail.data('orderId', order.odId)
  $detail.data('carId', order.odCarId)
  $detail.data('parkSiteId', order.odParkSiteId)
  $detail.data('needPay', need_pay)

  $parkAddress = $park.find('.address')
  $parkAddress.data('lng', park.otPointX)
  $parkAddress.data('lat', park.otPointY)
  gen_barcode(order.odBarcode)

# 订单查询
underway_order = (callback) ->
  msgLoading = gen_msg_loading()
  token = get_token()
  params = {
    token: token,
  }
  $.ajax
    url: '/hhw/v2/order/underwayOrder'
    data: params,
    dataType: 'json'
    cache: false
    success: (result) ->
      msgLoading.destroy()

      cb = (ret) ->
        if !ret
          alert '未查询到相关信息'
          history.go -1
        else
          callback(ret)
      request_filter(result, cb)
    error: (e) ->
      msgLoading.destroy()
      console.log('Error Derail:', e)

# 查询结算数据
query_bill_info = () ->
  underway_order(gen_bill_info)
  # msgLoading = gen_msg_loading()
  # token = get_token()
  # params = {
  #   token: token,
  # }
  # $.ajax
  #   url: '/hhw/v2/order/underwayOrder'
  #   data: params,
  #   dataType: 'json'
  #   cache: false
  #   success: (result) ->
  #     msgLoading.destroy()
  #     request_filter(result, gen_bill_info)
  #   error: (e) ->
  #     msgLoading.destroy()
  #     console.log('Error Derail:', e)

# 生成结算页面数据
gen_bill_info = (bill) ->
  if !bill
    alert('未查询到相关信息')
    history.go -1
    return
  park = bill.parkSite

  $bill = $('.bill')
  $park = $bill.find('.park_info')
  $bill_info = $bill.find('.bill_info')
  $bill_detail = $bill.find('.bill_detail')

  $park.find('.park_name').text(park.otName)
  $park.find('.license_plate').text(bill.odCarNum)

  total_price = bill.odAddPrice
  first_price = bill.odFirstPrice
  second_price= total_price - first_price

  start_time = bill.odStartTime
  end_time = bill.odEndTime
  # 离场时间的结束时间 +15min
  leave_time = end_time + (15 * 60 * 1000)
  leave_time = end_time
  park_time = end_time - start_time

  unit_price = park.otPrice
  need_pay = gen_need_pay(end_time, unit_price)
  console.log 'need_pay', need_pay
  if need_pay is 0
    $('.bill .action .weui-btn').addClass('weui-btn_disabled')

  $bill_info.find('.total_price').text('¥ ' + $.toDecimal2(total_price))
  $bill_info.find('.first_price').text('¥ ' + $.toDecimal2(first_price))
  $bill_info.find('.leave_time').text($.formatTime(new Date(leave_time)))
  $bill_detail.find('.start_time').text($.formatTime(new Date(start_time)))

  $park_time = $bill_detail.find('.park_time')
  #setInterval ->
  $park_time.text $.timediff park_time
  #, 1000
  $bill_detail.find('.first_price').text('¥ ' + $.toDecimal2(first_price))
  $bill_detail.find('.add_price').text('¥ ' + $.toDecimal2(second_price))

  $bill.data('orderId', bill.odId)
  $bill.data('carId', bill.odCarId)
  $bill.data('parkSiteId', bill.odParkSiteId)
  $bill.data('needPay', need_pay)


  gen_barcode(bill.odBarcode)

# 延长停车时间
extend_parking_action = () ->
    $bill = $('.bill')
    need_pay = $bill.data('needPay')
    return if need_pay is 0

    msgLoading = gen_msg_loading()
    token = get_token()

    order_id = $bill.data('orderId')
    car_id = $bill.data('carId')
    park_site_id = $bill.data('parkSiteId')

    params = {
      "orderId": order_id,
      "price": need_pay = 0.01,
      'token': token,
      'carId': car_id,
      'catalog': 1,
      'parkSiteId': park_site_id,
    }
    place_order_success_cb = () ->
      msgLoading.destroy()
    wx_pay_success_cb = (res) ->
      console.log('extend_parking_action 支付成功后的回调函数', res)
      location.reload()
    place_order need_pay isnt 0, params, place_order_success_cb, wx_pay_success_cb

request_filter = (result, callback) ->
  console.log result
  code = result.code
  message = result.message
  if code
    if code is '1001' 
      ret = result.data
      callback(ret)
    else
      alert message
  else alert 'Internal error [code undefined]'

# 生成 Barcode
gen_barcode = (content) ->
  JsBarcode("#barcode", content, {
    format: "code39",
    margin:1,
    fontSize:20,
    height:150
  });

# 计算结算需要补缴金额
# end_time 结束时间戳
# unit_price 单价
gen_need_pay = (end_time, unit_price) ->
  need_pay = 999
  try
    need_pay = 0
    now_time = Date.now()
    apart = end_time - now_time
    if apart > 0 or apart is 0
      return 0
    # 已停车时间 单位：h
    apart_hour = Math.abs(apart) / (1000 * 60 * 60)
    # 保留一位小数
    keep_decimal = parseFloat(apart_hour).toFixed(1)
    arr = String(keep_decimal).split('.')
    hour = arr[0]
    half_hour = arr[1]
    # 补缴金额
    need_pay = hour * unit_price
    if half_hour >  5
      need_pay += unit_price
    else if half_hour isnt 0
      need_pay += 0.5 * unit_price
  catch e
    console.log('Error Derail:', e)
  return need_pay


