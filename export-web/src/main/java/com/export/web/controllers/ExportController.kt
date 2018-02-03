package com.export.web.controllers

import com.export.web.vo.ExportVO
import com.export.web.vo.SheetOneVO
import com.export.web.vo.SheetTwoVO
import com.export.xls.starter.common.ExportEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

/**
 * 导出示例
 * @author liucheng
 * @create 2018-01-30 15:52
 **/
@RestController
@RequestMapping("/xls")
class ExportController @Autowired
constructor(private val exportEntityService: ExportEntityService) {
    /**
     * 导出
     */
    @RequestMapping("/export", method = [RequestMethod.GET])
    fun export(response: HttpServletResponse) {
        val index = 0
        val sheetOneVOs = ArrayList<SheetOneVO>()
        while (index < 10) {
            sheetOneVOs.add(SheetOneVO.builder().name("name张三" + index).pwd("pwd123456").build())
        }
        val sheetTwoVOs = ArrayList<SheetTwoVO>()
        while (index < 10) {
            sheetTwoVOs.add(SheetTwoVO.builder().a("a" + index).b("b123456").build())
        }

        val exportVO = ExportVO.builder().sheetOneVOs(sheetOneVOs).sheetTwoVOs(sheetTwoVOs).build()
        exportEntityService.export(response, exportVO)
    }
}
