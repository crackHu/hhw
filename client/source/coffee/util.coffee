_authorizationLink = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx219c336fa191f482&redirect_uri=http%3A%2F%2Fdev.basoft.cn%2Fhhw%2Fhome%2Fcode.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect'

$.MsgBox = (message, type = 1 ,lifetime = 0) ->
#  1: message
#  2: loading
#  3: success
  self = this
  $element = $('.msgBox_' + type)

  self.init = ->
    if $('.msgBox_' + type).length is 0
      msgBox = (
        '<div class="msgBox_' + type + '" style="display: none">' +
          '<div class="mask_transparent"></div><div class="weui-toast">' +
          '<i class="weui-icon_toast ' + (if type is 2 then 'weui-loading' else 'weui-icon-success-no-circle' ) + '"/>' +
          '<p class="weui-toast__content">' + message + '</p>' +
          '</div>' +
        '</div>'
      )
      $('body').append(msgBox)
      $element = $('.msgBox_' + type)

  self.show = ->
    $element.fadeIn 100

  self.hide = ->
    $element.fadeOut 100

  self.destroy = ->
    $element.fadeOut 100
    setTimeout (->
      $element.remove()
    ), 200

  self.init message,type
  self.show()
  if lifetime > 0
    setTimeout (->
      self.hide()
      self.destroy()
    ), lifetime

# @param type Number
# 1: dialog
# @param config Object
# config = {
#   title: 'title',
#   content: 'content',
#   auxiliary: {
#     text: 'auxiliary',
#   },
#   main: {
#     text: 'main',
#     callback: function() { // return boolean is_close
#       alert('main')
#     },
#   }
# }
$.Dialog = (config, type = 1) ->
  self = this
  $element = $('.dialog_' + type)

  self.init = ->
    auxiliary = config.auxiliary || {}
    main = config.main || {}
    if $('.dialog_' + type).length is 0
      dialog = (
        '<div class="dialog_' + type + '" style="display: none;">' +
            '<div class="weui-mask" style="z-index: 1000;"></div>' +
            '<div class="weui-dialog" ' + (if type is 1 then 'style="position: fixed; top:20%"' else '') + '>' +
                (if type is 1 then '<div class="weui-dialog__hd"><strong class="weui-dialog__title">' + config.title + '</strong></div>' else '') +
                '<div class="weui-dialog__bd">' + config.content + '</div>' +
                '<div class="weui-dialog__ft">' +
                    (if type is 1 then '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default dialog_auxiliary">' + auxiliary.text + '</a>' else '') +
                    '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary dialog_main">' + main.text + '</a>' +
                '</div>' +
            '</div>' +
        '</div>'
      )

      # dialog = (
      #   '<div class="dialog_' + type + '" style="display: none;">' +
      #       '<div class="weui-mask"></div>' +
      #       '<div class="weui-dialog">' +
      #           '<div class="weui-dialog__hd"><strong class="weui-dialog__title">' + config.title + '</strong></div>' +
      #           '<div class="weui-dialog__bd">' + config.content + '</div>' +
      #           '<div class="weui-dialog__ft">' +
      #               '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default dialog_auxiliary">' + auxiliary.text + '</a>' +
      #               '<a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary dialog_main">' + main.text + '</a>' +
      #           '</div>' +
      #       '</div>' +
      #   '</div>'
      # )
      $('body').append(dialog)
      $element = $('.dialog_' + type)
      $(document).on 'touchend', '.dialog_' + type + ' .dialog_auxiliary', ->
        is_close = true
        is_close = auxiliary.callback() if auxiliary.callback
        self.destroy() if is_close
      $(document).on 'touchend', '.dialog_' + type + ' .dialog_main', ->
        is_close = true
        is_close = main.callback() if main.callback
        self.destroy() if is_close

  self.show = ->
    $element.fadeIn 100

  self.hide = ->
    $element.fadeOut 100

  self.destroy = ->
    $(document).off 'touchend', '.dialog_' + type + ' .dialog_auxiliary'
    $(document).off 'touchend', '.dialog_' + type + ' .dialog_main'

    $element.fadeOut 100
    setTimeout (->
      $element.remove()
    ), 200

  self.init type, config
  self.show()

$.getUrlParam = (name) ->
  reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
  r = window.location.search.substr(1).match(reg)
  if r != null
    return unescape(r[2])
  null

$(document).on 'touchend','div.back_button', (e) ->
  e.preventDefault()
  history.go -1

# 时间格式化
$.formatTime = (date, sp1 = '-', sp2 = ':') ->
  year = date.getFullYear()
  month = date.getMonth() + 1
  day = date.getDate()

  hour = date.getHours()
  minute = date.getMinutes()
  second = date.getSeconds()

  formatNumber = (n) ->
    n = n.toString()
    return if n[1] then n else '0' + n

  return [year, month, day].map(formatNumber).join(sp1) + ' ' + [hour, minute].map(formatNumber).join(sp2)

# 保留2位小数，如：2，会在2后面补上00.即2.00 
$.toDecimal2 = (x) ->
  f = parseFloat(x); 

  if isNaN(f) then return false;

  f = Math.round(x*100)/100; 
  s = f.toString(); 
  rs = s.indexOf('.'); 

  if rs < 0 
    rs = s.length; 
    s += '.'; 

  s += '0' while s.length <= rs + 2
  return s

# 时间差
$.timediff = (apart_ts) ->
  ret = "";  
  d_seconds = apart_ts / 1000;  
  if d_seconds < 0
   return -1;

  fillZero = (i) ->
    if i < 10 then i = "0" + i
    return i

  d_days = parseInt(d_seconds / 86400);  
  d_hours = parseInt(d_seconds / 3600) - d_days * 24;  
  d_minutes = parseInt(d_seconds / 60) - d_days * 24 * 60 - d_hours * 60;
  d_seconds = d_seconds - d_days * 24 * 60 * 60 - d_hours * 60 * 60 - d_minutes * 60

  if d_days > 0
    ret += fillZero(d_days) + "天 ";
  if d_hours > 0
    ret += fillZero(d_hours) + ":";
  else
    ret += "00:"
  if d_minutes > 0 
    ret += fillZero(d_minutes) + ":";
  else
    ret += "00:"
  if d_seconds > 0 
    ret += fillZero(d_seconds);
  else
    ret += "00"
  return ret;

