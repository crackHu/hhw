//const proxy = 'http://172.16.1.127:8080'
//const localhost = 'http://172.16.1.213:8080'

export const __DEBUG__ = process.env.NODE_ENV === 'development'

export const APP = {
	host: '172.16.1.213:8080',
	projectName: 'vds5s3_user',
	buildPath: 'build',

	getBaseUrl: function() {
		return __DEBUG__ ? `/${this.projectName}` : `http://${this.host}/${this.projectName}`
	}
}

export const DATE_FORMAT_STRING = 'YYYY-MM-DD HH:mm:ss'

/*功能配置*/
export const FUNCTION = {
	declare: {
		CREATE: 'CREATE',
		DELETE: 'DELETE',
		UPDATE: 'UPDATE',
		QUERY: 'QUERY',
	},
	/*该插件拥有的功能*/
	plugin: {
		CREATE: true,
		DELETE: true,
		UPDATE: true,
		QUERY: true
	}
}

export const API = {
	/*请求成功标识*/
	reqCorrectCode: ['code', 'code === \'1001\''],
	reqMessage: 'message',
	reqData: 'data',

	module: {
		base: `${APP.getBaseUrl()}/v2/admin/user/usertype`,

		findByPage: function() {
			return `${this.base}/findByPage`
		},
		showById: function() {
			return `${this.base}/showById`
		},
		addEntity: function() {
			return `${this.base}/addEntity`
		},
		editByEntity: function() {
			return `${this.base}/editByEntity`
		},
		delById: function() {
			return `${this.base}/delById`
		},
	}

}

//************* Entity Config *************//
export const EntityConfig = {
	showById: {
		property: 'data',
		fields: [
			'utId',
		'utName',
		'utIsRemove',

		],
		multiFields: [],
		dateFields: [
			'utCreateTime',
		'utModifyTime',

		],
		cascadeFields: [],

	}
}

//************* Edit Table Config *************//
export const EditTableConfig = {
	/*数据源 属性*/
	dataSourcePro: 'data.content',
	dataSourceTotal: 'data.totalElements',
	/*表格字段*/
	columnsId: 'utId',
	columnsDisplay: '用户类型名称',
	defaultSort: 'utModifyTime,desc',
	defaultPage: 1,
	defaultSize: 10,
	columns: [
	{
	title: '用户类型名称',
	dataIndex: 'utName',
	width: '10%',
	remder:{
		link: true,
		onclick: true,
		modaltitle: '查看 - {utName}',
		},	},	{
	title: '是否删除',
	dataIndex: 'utIsRemove',
	},	{
	title: '创建时间',
	dataIndex: 'utCreateTime',
	width: '10%',
	render: {
		format: 'YYYY-MM-DD HH:mm:ss',
		},
	},
	{
	title: '修改时间',
	dataIndex: 'utModifyTime',
	width: '10%',
	render: {
		format: 'YYYY-MM-DD HH:mm:ss',
		},
	},

	],
	/*表单*/
	form: {
		width: 1000,
		//vertical horizontal inline
		layout: {
			'vertical': true
		},
		/*表单控件*/
		item: [{
		label: '用户类型名称',
		name: 'utName',
		type: 'input',
		required: 'true',
		message: '用户类型名称不能为空',
		showIn: {			CREATE: true,
			UPDATE: true,
			QUERY: true		},
		config: {			placeholder: '請輸入用户类型名称'
		}
		},
{
		label: '',
		name: 'utIsRemove',
		type: 'input',
		required: 'true',
		message: '不能为空',
		showIn: {			CREATE: true,
			UPDATE: true,
			QUERY: true		},
		config: {			placeholder: '請輸入'
		}
		},
{
		label: '',
		name: 'utCreateTime',
		type: 'datepicker',
		format: DATE_FORMAT_STRING,
		required: 'true',
		message: '不能为空',
		showIn: {
			CREATE: false,
			UPDATE: false,
			QUERY: true
		},
		config: {
			showTime: true,
			style: {
			width: 197.33
		}
		}
		},
{
		label: '',
		name: 'utModifyTime',
		type: 'datepicker',
		format: DATE_FORMAT_STRING,
		required: 'true',
		message: '不能为空',
		showIn: {
			CREATE: false,
			UPDATE: false,
			QUERY: true
		},
		config: {
			showTime: true,
			style: {
			width: 197.33
		}
		}
		},
],
		/*控件布局*/
		itemLayout: {
			labelCol: {
				span: 8
			},
			wrapperCol: {
				span: 16
			},
		},
	}
}
