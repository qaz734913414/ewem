<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="数据编码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入数据编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入数据名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="数据类型" clearable size="small">
          <el-option
            v-for="dict in basicDataTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['basicdata:basicData:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basicdata:basicData:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basicdata:basicData:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['basicdata:basicData:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="basicDataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="数据类型" align="center" prop="id" v-if="false"/>
      <el-table-column label="数据编码" align="center" prop="code"/>
      <el-table-column label="数据名称" align="center" prop="name"/>
      <el-table-column label="数据类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="basicDataTypeOptions" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basicdata:basicData:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basicdata:basicData:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改基础数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="33%" >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数据编码" prop="code">
          <el-input v-model="form.code" placeholder="请输入数据编码" style="width: 300px"/>
        </el-form-item>
        <el-form-item label="数据名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入数据名称" style="width: 300px"/>
        </el-form-item>
        <el-form-item label="数据类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择数据类型">
            <el-option
              v-for="dict in basicDataTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>

        <template style="width: 200px">
          <el-transfer
            :titles="['源数据', '已选择']"
            filterable
            filter-placeholder="请输入名称"
            v-model="form.addMetadataIds"
            :data="metadataOptions">
          </el-transfer>
        </template>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    addBasicData,
    delBasicData,
    exportBasicData,
    getBasicData,
    listBasicData,
    updateBasicData
  } from '@/api/code/basicData'

  import { listMetadata } from '@/api/code/metadata'

  export default {
    name: 'BasicData',
    data() {
      return {
        // 按钮loading
        buttonLoading: false,
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 基础数据表格数据
        basicDataList: [],
        // 状态数据字典
        statusOptions: [],
        // 基础数据类型字典
        basicDataTypeOptions: [],
        // 元数据字典
        metadataOptions: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          code: undefined,
          name: undefined,
          type: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {},
        conditionForm: {
          introductionList: [{
            id: ''
          }]
        }
      }
    },
    created() {
      this.getList()
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.getDicts('ewem_basicdata_type').then(response => {
        this.basicDataTypeOptions = response.data
      })
    },
    methods: {
      /** 查询基础数据列表 */
      getList() {
        this.loading = true
        listBasicData(this.queryParams).then(response => {
          this.basicDataList = response.rows
          this.total = response.total
          this.loading = false
        })
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          code: undefined,
          name: undefined,
          type: undefined,
          addMetadataIds: [],
          status: undefined,
          delFlag: undefined,
          createBy: undefined,
          createTime: undefined,
          updateBy: undefined,
          updateTime: undefined,
          remark: undefined
        }
        this.resetForm('form')
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm('queryForm')
        this.handleQuery()
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加基础数据'
        this.handleMetadata()
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.loading = true
        this.reset()
        const id = row.id || this.ids
        getBasicData(id).then(response => {
          this.loading = false
          this.form = response.data
          this.form.addMetadataIds = []
          this.open = true
          this.title = '修改基础数据'
        })
        this.handleMetadata()
      },

      handleMetadata() {
        this.metadataOptions = []
        listMetadata({}).then((response) => {
          response.rows.forEach((bc, index) => {
            this.metadataOptions.push({
              label: bc.name,
              key: bc.id
            })
          })
        })
      },

      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            this.buttonLoading = true
            if (this.form.id != null) {
              updateBasicData(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }).finally(() => {
                this.buttonLoading = false
              })
            } else {
              addBasicData(this.form).then(response => {
                this.msgSuccess('新增成功')
                this.open = false
                this.getList()
              }).finally(() => {
                this.buttonLoading = false
              })
            }
          }
        })
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids
        const name = row.name || this.name
        this.$confirm('是否确认删除[' + name + ']?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true
          return delBasicData(ids)
        }).then(() => {
          this.loading = false
          this.getList()
          this.msgSuccess('删除成功')
        }).finally(() => {
          this.loading = false
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.exportLoading = true
          return exportBasicData(queryParams)
        }).then(response => {
          this.download(response.msg)
          this.exportLoading = false
        }).catch(() => {
        })
      }
    }
  }
</script>
