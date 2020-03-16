package edp.catt;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author dengliming
 * @Date: 2020/3/12 9:49 上午
 */


public class TestStringTemplate {

    public static void main(String[] args) {
        ST hello = new ST("Hello <name>");
        hello.add("name","World");
        System.out.println(hello.render());
    }

    @Test
    public void test1 (){
        char s = 36;

        ST st2 = new ST("select $ first(columns:t1(),t2())$ from users",s,s);
        st2.add("columns","a");
        st2.add("columns","b");
        st2.add("t1","method(name,body,cleanup) ::= <<\n" +
                "void <name>() {\n" +
                "  <body>\n" +
                "  <cleanup>\n" +
                "}\n" +
                ">>");
        st2.add("t2","method(name,body,cleanup) ::= <<\n" +
                "void <name>() {\n" +
                "  <body>\n" +
                "  <cleanup>\n" +
                "}\n" +
                ">>");
//        st2.add("dilimiter",",");
        System.out.println(st2.render());
    }

    @Test
    public void test2(){
        ST st = new ST("select <rest(columns)>,<obj.name>,<obj.age> from user");
        st.add("columns","a");
        st.add("columns","b");
        Map<String,String> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","111");
        st.add("obj", JSON.parseObject("{\"name\":\"zhangsan\",\"age\":11}"));
//        st2.add("dilimiter",",");

        System.out.println(st.render());
    }


    @Test
    public void test3(){
        String simple = "SimpleST $attribute;separator=\" \"$ \n" +
                "$first(attribute);separator=\" \"$ \n" +
                "$last(attribute);separator=\" \"$ \n" +
                "$rest(attribute);separator=\" \"$ \n" +
                "$trunc(attribute);separator=\" \"$ \n" +
                "$strip(attribute);separator=\" \"$ \n" +
                "$length(attribute)$ \n" +
                "$attr.name$=$attr.value$ \n" +
                "$[attribute, attr];separator=\" \"$ \n" +
                "$\\u2021$";
        //new SimpltTemplate 对象
        ST simpleST = new ST(simple,(char) 36,(char)36);
        //attribute设置
        simpleST.add("attribute", null);
        //注意：不同于MAP，对同一个attribute多次赋值时，不会覆盖，而是将新旧值合并成一个数组,并且会忽略null值
        simpleST.add("attribute", "attr1");
        simpleST.add("attribute", "attr2");
        //可以比较两种方式的区别
//      simpleST.setAttribute("attribute", Arrays.asList(new String[]{null, "attr1", "attr2"}));
        Map<String, String> attr = new HashMap<String, String>();
        //可以通过map.key的方式获取map中的值
        attr.put("name", "attr");
        attr.put("value", "val");
        simpleST.add("attr", attr);
        //输出
        System.out.println(simpleST.render());

        //综合应用
        System.out.println("\n---------------------------------------\n");
//        String template = "Student[name=$stu.name$ \n" +
//                "\t \\$optional=$stu.(\"\\$optional\")$ \n" +
//                "\t classes=$stu.classes;null=\"XXX\",separator=\" \"$ \n" +
//                "\t teachers=$stu.teachers;null=\"...\",separator=\",\"$ ]";
//        ST st = new ST(template);
//        Student stu = new Student();
//        stu.setName("student1");
//        stu.set$optional("false");
//        stu.setClasses(Arrays.asList(new String[]{"Math", "English", null}));
////      注意Map的迭代结果只输出value
//        Map<String, String> teachers = new HashMap<String, String>();
//        teachers.put("Math", "Mr. M");
//        teachers.put("English", "Miss E");
//        teachers.put(null, null);
//        stu.setTeachers(teachers);
//        st.setAttribute("stu", stu);
//        System.out.println(st);

        //模板调用 必须在同一个StringTemplateGroup中的StringTemplate才能相互调用
        System.out.println("\n---------------------------------------\n");
        //new StringTemplateGroup 对象
//        STGroup stGroup = new STGroup("test");
//        //在stGroup中定义一个名字为method的StringTemplate
//        stGroup.defineTemplate("method", "\n method invoke $attr$");
//        stGroup.defineTemplate("main", "main $method(attr=attribute)$");
//        stGroup.defineTemplate("element", "\n $i$ $i0$ $it$");
////      stGroup.defineTemplate("iterator", "Iterator $list:element()$ \n $list:{it1| \n $it1$ }$");
////      stGroup.defineTemplate("iterator", "Iterator $list:element():method()$");
////      stGroup.defineTemplate("iterator", "Iterator $list:element(),method()$");
//        stGroup.defineTemplate("iterator", "Iterator $list,list1:{arg1,arg2| \n $arg1$ $arg2$}$");
//        //获得stGroup中名为main的StringTemplate
//        StringTemplate main = stGroup.getInstanceOf("main");
//        main.setAttribute("attribute", "attri");
//        System.out.println(main);
//        StringTemplate iterator = stGroup.getInstanceOf("iterator");
//        iterator.setAttribute("list", Arrays.asList(new String[]{null, "a", "b", "c", "d"}));
//        iterator.setAttribute("list1", Arrays.asList(new String[]{"a1", "b1", "c1"}));
//        System.out.println(iterator);


}


}
