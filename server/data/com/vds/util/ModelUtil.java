package com.vds.util;

import java.io.File;

public class ModelUtil {
	static void GenerateModel(BaseModel baseModel) throws Exception {
		String entry = DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", ""));
		String model = "other";
		String packagePath = "com.vds.app." + model + ".model";
		String rootPagePath = "com.vds.app." + model;
		String rootPath = baseModel.getPackageUrl() + "\\src\\main\\java\\com\\vds\\app\\" + model;

		// String content = ModelUtil.parse(baseModel, packagePath);
		// // 创建包
		//
		// String path = baseModel.getPackageUrl() +
		// "\\src\\main\\java\\com\\vds\\app\\" + model + "\\model";
		// File file = new File(path);
		// if (!file.exists()) {
		// file.mkdirs();
		// }
		// String resPath = path + "/" +
		// DatabaseUtils.initcap(baseModel.getTableName().replaceAll("tb_", ""))
		// + ".java";
		// System.out.println(resPath);
		// DatabaseUtils.writeStringToFile(new File(resPath), content);
		writeByType(baseModel, rootPagePath, rootPath, model, "model");

		writeByType(baseModel, rootPagePath, rootPath, model, "jpa");

		/**
		 * 生成service
		 */
		writeByType(baseModel, rootPagePath, rootPath, model, "service");

		writeByType(baseModel, rootPagePath, rootPath, model, "impl");

		/**
		 * 生成controller
		 */
		writeByType(baseModel, rootPagePath, rootPath, model, "controller");
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	// static String parse(List<String> colNames, List<String> colTypes, int[]
	// colSizes, String packagePath, String tableName) {
	static String parse(BaseModel baseModel, String packagePath,String model) {

		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".model;\r\n\r\n");
		if (baseModel.isFutil()) {
			sb.append("import java.util.Date;\r\n");
		}
		if (baseModel.isFsql()) {
			sb.append("import java.sql.*;\r\n");
		}
		sb.append("import java.io.Serializable;\r\n");
		sb.append("import javax.persistence.Column;\r\n");
		sb.append("import javax.persistence.Entity;\r\n");
		sb.append("import javax.persistence.Id;\r\n");
		sb.append("import javax.persistence.Table;\r\n");
		sb.append("import javax.persistence.GeneratedValue;\r\n");
		sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
		sb.append("import org.springframework.context.annotation.Scope;\r\n\r\n");
		sb.append("@Entity\r\n");
		sb.append("@Table(name = \"" + baseModel.getTableName() + "\")\r\n");
		sb.append("@Scope(value = \"prototype\")\r\n");
		sb.append("public class " + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + " implements Serializable{\r\n\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
		processAllAttrs(baseModel, sb);
		sb.append("\r\n");
		processAllMethod(baseModel, sb);
		sb.append("}\r\n");
		System.out.println(sb.toString());
		return sb.toString();

	}

	/**
	 * 
	 * @param baseModel
	 * @param packagePath
	 * @return
	 */
	static String jpaParse(BaseModel baseModel, String packagePath,String model) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".jpa;\r\n\r\n");
		sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
		sb.append("import " + packagePath + ".model." + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + ";\r\n\n");

		sb.append("public interface " + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Jpa extends JpaRepository<"
				+ DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + ", String>{\r\n\r\n");
		sb.append("}\r\n");
		return sb.toString();
	}

	/**
	 * 
	 * @param baseModel
	 * @param packagePath
	 * @return
	 */
	static String serviceParse(BaseModel baseModel, String packagePath,String model) {

		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".service;\r\n\r\n");

		sb.append("import org.springframework.data.domain.Pageable;\r\n");
		sb.append("import com.vds.app.base.BaseService;\r\n");
		sb.append("import com.vds.app.exception.Msg;\r\n");
		sb.append("import com.vds.app.exception.MyException;\r\n");
		sb.append("import " + packagePath + ".jpa." + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Jpa;\r\n\n");
		sb.append("import " + packagePath + ".model." + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + ";\r\n\n");

		sb.append("public interface " + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Service extends BaseService<"
				+ DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + ">{\r\n\r\n");

		sb.append("\tpublic Msg findAll(Pageable pageable);\r\n");

		sb.append("}\r\n");
		return sb.toString();
	}

	static String controllerParse(BaseModel baseModel, String packagePath,String model) {

		String Upname = DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", ""));
		String allLowName = DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")).toLowerCase();

		String name1 = DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Service";
		String name2 = DatabaseUtils.initcap2(baseModel.getTableName().replace("tb_", "")) + "Service";

		String sb = FileUtil.txt2String(new File(ModelUtil.class.getClass().getResource("/").getPath() + "controller.txt"));

		sb = sb.replace("SmsCpController", Upname + "Controller");
		sb = sb.replace("com.vds.app.sms", packagePath);

		sb = sb.replace("SmsCpService", name1);
		sb = sb.replace("smsCpService", name2);
		sb = sb.replace("url", allLowName);
		sb = sb.replace("Entitys", Upname);
		sb = sb.replace("modules", model);
		return sb;
	}

	static String serviceImplParse(BaseModel baseModel, String packagePath,String model) {
		String name = DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Jpa";
		String name2 = DatabaseUtils.initcap2(baseModel.getTableName().replace("tb_", "")) + "Jpa";
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".service.impl;\r\n\r\n");

		sb.append("import javax.inject.Inject;\r\n");
		sb.append("import com.vds.app.exception.Msg;\r\n");
		sb.append("import org.springframework.data.domain.Page;\r\n");
		sb.append("import org.springframework.data.domain.Pageable;\r\n");
		sb.append("import org.springframework.stereotype.Service;\r\n");
		sb.append("import com.vds.app.base.BaseServiceImpl;\r\n");
		sb.append("import " + packagePath + ".model." + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + ";\r\n\n");
		sb.append("import " + packagePath + ".jpa." + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Jpa;\r\n\n");
		sb.append("import " + packagePath + ".service." + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Service;\r\n\n");

		sb.append("@Service\r\n");
		sb.append("public class " + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "ServiceImpl extends BaseServiceImpl<"
				+ DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "> implements "
				+ DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "Service{\r\n\r\n");

		sb.append("\t@Inject\r\n");
		sb.append("\tprivate " + name + " " + name2 + ";\r\n\n");

		sb.append("\tpublic Msg findAll(Pageable pageable) {\r\n");

		sb.append("\t\tPage<" + DatabaseUtils.initcap(baseModel.getTableName().replace("tb_", "")) + "> list = " + name2 + ".findAll(pageable);\r\n");
		sb.append("\treturn Msg.MsgSuccess(list);\r\n");
		sb.append("\t}\r\n");

		sb.append("}\r\n");
		return sb.toString();
	}

	static String methodParse(BaseModel baseModel, String packagePath, String typeName,String model) {
		switch (typeName) {
		case "model":
			return parse(baseModel, packagePath,model);
		case "service":
			return serviceParse(baseModel, packagePath,model);
		case "impl":
			return serviceImplParse(baseModel, packagePath,model);
		case "jpa":
			return jpaParse(baseModel, packagePath,model);
		case "controller":
			return controllerParse(baseModel, packagePath,model);
		}

		return "";
	}

	/**
	 * 生成所有的方法 （生成的model）
	 * 
	 * @param baseModel
	 * 
	 * @param sb
	 */
	static void processAllMethod(BaseModel baseModel, StringBuffer sb) {
		for (int i = 0; i < baseModel.getColnames().size(); i++) {
			sb.append("\tpublic void set" + DatabaseUtils.initcap(baseModel.getOrgcolnames().get(i)) + "("
					+ sqlType2JavaType(baseModel.getColTypes().get(i)) + " " + baseModel.getColnames().get(i) + "){\r\n");
			sb.append("\t\tthis." + baseModel.getColnames().get(i) + "=" + baseModel.getColnames().get(i) + ";\r\n");
			sb.append("\t}\r\n\r\n");

			sb.append("\tpublic " + sqlType2JavaType(baseModel.getColTypes().get(i)) + " get"
					+ DatabaseUtils.initcap(baseModel.getOrgcolnames().get(i)) + "(){\r\n");
			sb.append("\t\treturn " + baseModel.getColnames().get(i) + ";\r\n");
			sb.append("\t}\r\n\r\n");
		}
	}

	/**
	 * 解析输出属性
	 * 
	 * @param baseModel
	 * 
	 * @return
	 */
	static void processAllAttrs(BaseModel baseModel, StringBuffer sb) {
		for (int i = 0; i < baseModel.getColnames().size(); i++) {
			if (i == 0) {
				sb.append("\t@Id\r\n");
				sb.append("\t@GeneratedValue(generator = \"system-uuid\")\r\n");
				sb.append("\t@GenericGenerator(name = \"system-uuid\", strategy = \"uuid\")\r\n");
			}
			sb.append("\t@Column(name = \"" + baseModel.getOrgcolnames().get(i) + "\")\r\n");
			sb.append("\tprivate " + sqlType2JavaType(baseModel.getColTypes().get(i)) + " " + baseModel.getColnames().get(i) + ";//"
					+ baseModel.getMemos().get(i) + "\r\n");
		}
	}

	static String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "bool";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("timestamp")) {
			return "Date";
		}

		else if (sqlType.equalsIgnoreCase("image")) {
			return "Blob";
		} else if (sqlType.equalsIgnoreCase("text")) {
			return "Clob";
		}
		return null;
	}

	static void writeByType(BaseModel baseModel, String rootPagePath, String rootPath, String model, String typeName) throws Exception {
		String content = ModelUtil.methodParse(baseModel, rootPagePath, typeName,model);
		String fileCase = DatabaseUtils.initcap(typeName).replace("Model", "");

		String resPath = rootPath + "/" + typeName + "/" + DatabaseUtils.initcap(baseModel.getTableName().replaceAll("tb_", "")) + fileCase + ".java";
		if (fileCase.equals("Controller")) {
			resPath = rootPath + "/" + typeName + "/Admin" + DatabaseUtils.initcap(baseModel.getTableName().replaceAll("tb_", "")) + fileCase
					+ ".java";
		}
		String path = baseModel.getPackageUrl() + "\\src\\main\\java\\com\\vds\\app\\" + model + "\\" + typeName;
		if (typeName.equals("impl")) {
			resPath = rootPath + "/service/" + typeName + "/" + DatabaseUtils.initcap(baseModel.getTableName().replaceAll("tb_", "")) + "Service"
					+ fileCase + ".java";
			path = baseModel.getPackageUrl() + "\\src\\main\\java\\com\\vds\\app\\" + model + "\\service\\" + typeName;
		}
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (new File(resPath).exists()) {
			return;
		}
		DatabaseUtils.writeStringToFile(new File(resPath), content);
	}
}
