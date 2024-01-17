import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.aenlly.acg.AcgApplication;

import javax.sql.DataSource;

/**
 * 相关依赖 1.mybatis-plus-boot-starter 2.mybatis-plus-generator 3.velocity-engine-core
 */
@SpringBootTest(classes = AcgApplication.class)
public class Generator {

  @Autowired
  private DataSource dataSource;

  /**
   * 需要配置
   */
  private final String author = "Aenlly"; // 作者

  private final String packageName = "top.aenlly.acg"; // 项目主路径包名(com.boot01.test01)
  private final String dbTables = "test"; // 需要生成的表名
  private final Boolean enableSwagger = true; // 是否开启Swagger

  @Test
  public void main() {

    // 1.数据源配置
    DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(dataSource);

    // 2.全局配置
    GlobalConfig.Builder globalConfigBuilder = GeneratorBuilder.globalConfigBuilder();
    // 代码生成目录
    String projectPath = System.getProperty("user.dir");
    globalConfigBuilder.outputDir(projectPath + "/src/test/java");
    // 作者
    globalConfigBuilder.author(author);
    // 结束时是否打开文件夹
    globalConfigBuilder.openDir(false);

    // 实体属性Swagger2注解
    if (enableSwagger) {
      globalConfigBuilder.enableSwagger();
    }

    // 3.包配置
    PackageConfig.Builder packageConfigBuilder = new PackageConfig.Builder();
    packageConfigBuilder.parent(packageName); // 项目包名

    // 都有默认值   配置实体、mapper、service、controller的包名
    // packageConfigBuilder.entity("entity");

    // 4.策略配置
    StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
    // 设置需要映射的表名  用逗号分割
    strategyConfigBuilder.addInclude(dbTables.split(","));
    // 下划线转驼峰
    strategyConfigBuilder.entityBuilder().naming(NamingStrategy.underline_to_camel);
    strategyConfigBuilder.entityBuilder().columnNaming(NamingStrategy.underline_to_camel);
    // entity的Lombok
    strategyConfigBuilder.entityBuilder().enableLombok();
    // 逻辑删除
    strategyConfigBuilder.entityBuilder().logicDeleteColumnName("deleted");
    strategyConfigBuilder.entityBuilder().logicDeletePropertyName("deleted");
    // 自动填充
    // 创建时间
    IFill createTime = new Property("create_time", FieldFill.INSERT);
    // 更新时间
    IFill updateTime = new Property("update_time", FieldFill.INSERT_UPDATE);
    strategyConfigBuilder.entityBuilder().addTableFills(createTime, updateTime);
    // 乐观锁
    strategyConfigBuilder.entityBuilder().versionColumnName("version");
    strategyConfigBuilder.entityBuilder().versionPropertyName("version");
    // 使用RestController
    strategyConfigBuilder.controllerBuilder().enableRestStyle();
    // 将请求地址转换为驼峰命名，如 http://localhost:8080/hello_id_2
    strategyConfigBuilder.controllerBuilder().enableHyphenStyle();

    // 创建代码生成器对象，加载配置   对应1.2.3.4步
    AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfigBuilder.build());
    autoGenerator.global(globalConfigBuilder.build());
    autoGenerator.packageInfo(packageConfigBuilder.build());
    autoGenerator.strategy(strategyConfigBuilder.build());

    // 执行
    autoGenerator.execute();
  }
}
