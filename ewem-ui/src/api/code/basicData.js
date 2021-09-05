import request from '@/utils/request'

// 查询基础数据列表
export function listBasicData(query) {
  return request({
    url: '/basicdata/basicData/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据详细
export function getBasicData(id) {
  return request({
    url: '/basicdata/basicData/' + id,
    method: 'get'
  })
}

// 新增基础数据
export function addBasicData(data) {
  return request({
    url: '/basicdata/basicData',
    method: 'post',
    data: data
  })
}

// 修改基础数据
export function updateBasicData(data) {
  return request({
    url: '/basicdata/basicData',
    method: 'put',
    data: data
  })
}

// 删除基础数据
export function delBasicData(id) {
  return request({
    url: '/basicdata/basicData/' + id,
    method: 'delete'
  })
}
// 导出基础数据
export function exportBasicData(query) {
  return request({
    url: '/basicdata/basicData/export',
    method: 'get',
    params: query
  })
}

