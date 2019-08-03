import com.mybatis.ExcelUtils;
import com.mybatis.dao.EmployeeMapperAnnotation;
import com.mybatis.entity.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/6/19 16:24
 *
 * @Author: created by admin
 * @Date: created in 16:24 2019/6/19
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class EmployeeAnnotationTest {

    @Test
    public void test2() {
        String fileName = "员工信息表"; //模板名称
        String[] handers = {"姓名","性别","证件类型","证件号码","服务结束时间","参保地","民族"}; //列标题

        //下拉框数据
        List<String[]> downData = new ArrayList();
        String[] str1 = {"男","女","未知"};
        String[] str2 = {"北京","上海","广州","深圳","武汉","长沙","湘潭"};
        String[] str3 = {"01-汉族","02-蒙古族","03-回族","04-藏族","05-维吾尔族","06-苗族","07-彝族","08-壮族","09-布依族", "10-朝鲜族","11-满族","12-侗族","13-瑶族","14-白族","15-土家族","16-哈尼族","17-哈萨克族","18-傣族","19-黎族","20-傈僳族","21-佤族","22-畲族","23-高山族","24-拉祜族","25-水族","26-东乡族","27-纳西族","28-景颇族","29-柯尔克孜族","30-土族", "31-达斡尔族","32-仫佬族","33-羌族","34-布朗族","35-撒拉族","36-毛难族","37-仡佬族","38-锡伯族","39-阿昌族","40-普米族", "41-塔吉克族","42-怒族","43-乌孜别克族","44-俄罗斯族","45-鄂温克族","46-德昂族","47-保安族","48-裕固族","49-京族","50-塔塔尔族", "51-独龙族","52-鄂伦春族","53-赫哲族","54-门巴族","55-珞巴族","56-基诺族","98-外国血统","99-其他"};
        downData.add(str1);
        downData.add(str2);
        downData.add(str3);
        String [] downRows = {"1","5","6"}; //下拉的列序号数组(序号从0开始)

        try {

            ExcelUtils.createExcelTemplate("G://newFile.xls",handers,downData,downRows);
//            ExcelUtil.getExcelTemplate(fileName, handers, downData, downRows, request, response);

        } catch (Exception e) {

        }

    }

    @Test
    public void tset1() throws IOException {
        SqlSession sqlSession = EmployeeTest.getSqlSession();
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        Employee employee = mapper.getEmployeeById(1);

        System.out.println(employee);
    }
}
