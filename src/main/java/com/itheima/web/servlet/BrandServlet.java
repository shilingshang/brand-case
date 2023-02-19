package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用Service查询
        List<Brand> brands = brandService.selectAll();
        //转化为Json数据
        String toJSONString = JSON.toJSONString(brands);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(toJSONString);

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转换为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service添加
        brandService.add(brand);
        //响应成功的标识
        response.getWriter().write("success");

    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受数据[1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转换为int[]数组
        int[] ids = JSON.parseObject(params, int[].class);
        //调用service添加
        brandService.deleteByIds(ids);
        //响应成功的标识
        response.getWriter().write("success");

    }

    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转换为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service添加
        brandService.updateById(brand);
        //响应成功的标识
        response.getWriter().write("success");


    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转换为brand对象
        //int id = JSON.parseObject(params, int.class);
        Integer id = JSON.parseObject(params, Integer.class);
        System.out.println("id: " + id);
        //调用service添加
        brandService.deleteById(id);
        //响应成功的标识
        response.getWriter().write("success");


    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数和每页展示条数
        //临时数据
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //2.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);


        //转化为Json数据
        String JSONString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONString);

    }

    /**
     * 分页条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数和每页展示条数
        //临时数据
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取对应的查询条件
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转换为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //2.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);


        //转化为Json数据
        String JSONString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSONString);

    }
}

