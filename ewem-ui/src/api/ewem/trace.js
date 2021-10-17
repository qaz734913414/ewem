import request from '@/utils/request'

// 查询码详细
export function getTrace(code) {
  return request({
    url: '/trace/' + code,
    method: 'get'
  })
}
