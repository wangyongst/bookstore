package com.myweb.controller;

import com.myweb.pojo.Book;
import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Record;
import com.myweb.service.TwoService;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@CrossOrigin("*")
@Controller
public class TwoController {

    @Autowired
    public TwoService twoService;


    public Result setRecordListNull(Result result) {
        for (int t = 0; t < ((List<Record>) result.getData()).size(); t++) {
            ((List<Record>) result.getData()).get(t).getBookstore().setRecord(null);
        }
        return result;
    }


    public Result setBookstoreNull(Result result) {
        for (int t = 0; t < ((Bookstore) result.getData()).getRecord().size(); t++) {
            ((Bookstore) result.getData()).getRecord().get(t).setBookstore(null);
        }
        return result;
    }

    @ApiOperation(value = "借书申请", notes = "借书申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "letter", value = "申请信（可选）", required = true, dataType = "Strig")
    })
    @ResponseBody
    @PostMapping("/borrow/request")
    public Result borrowRequest(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.borrowRequest(twoParameter));
    }

    @ApiOperation(value = "还书申请", notes = "还书申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "letter", value = "申请信（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/return/request")
    public Result returnRequest(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.returnRequest(twoParameter));
    }

    @ApiOperation(value = "拒绝扣押金", notes = "拒绝扣押金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
    })
    @ResponseBody
    @PostMapping("/return/disagree")
    public Result returnDisagree(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.returnDisagree(twoParameter));
    }

    @ApiOperation(value = "还书成功（还书人操作)", notes = "还书成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/return/agree")
    public Result returnAgree(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.returnAgree(twoParameter));
    }


    @ApiOperation(value = "我的借书/还书申请", notes = "我的借书申请列表，列表状态为2时可以进行开始借书操作，状态为5时可以进行支付押金操作，状态为7时可进行申请还书操作，状态为10时可进行拒绝扣押金操作或还书成功操作，状成为13时可进行确认收货操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "申请状态（可选，默认为0），0全部，1发起申请，2已同意，3已拒绝，4开始借书（不要押金），5待付押金，6,已付押金，7已经借出,8申请还书，9还书确认，10扣除押金，11拒绝扣押金，12还书成功,13已发货", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/myrequest")
    public Result borrowMyrequest(@ModelAttribute TwoParameter twoParameter) {
        return setRecordListNull(twoService.borrowMyrequest(twoParameter));
    }

    @ApiOperation(value = "向我借书/还书的申请", notes = "向我借书的申请列表,列表状态为1的可进行同意或拒绝，列表状态为4或6的可进行确认借书操作，状态为8时可进行确认还书操作，状态为9或11时可进行扣除押金操作或还书成功操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "申请状态（可选，默认为0），0全部，1发起申请，2已同意，3已拒绝，4开始借书（不要押金），5待付押金，6,已付押金，7已经借出,8申请还书，9还书确认，10扣除押金，11拒绝扣押金，12还书成功，13已发货", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/torequest")
    public Result borrowTorequest(@ModelAttribute TwoParameter twoParameter) {
        return setRecordListNull(twoService.borrowTorequest(twoParameter));
    }

    @ApiOperation(value = "同意借书", notes = "同意借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/borrow/agree")
    public Result borrowAgree(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.borrowAgree(twoParameter));
    }

    @ApiOperation(value = "确认收货", notes = "确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer")

    })
    @ResponseBody
    @PostMapping("/borrow/accept")
    public Result borrowccept(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.borrowAccept(twoParameter));
    }

    @ApiOperation(value = "开始借书", notes = "开始借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer")

    })
    @ResponseBody
    @PostMapping("/borrow/start")
    public Result borrowStart(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.borrowStart(twoParameter));
    }

    @ApiOperation(value = "拒绝借书", notes = "拒绝借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/borrow/disagree")
    public Result borrowDisagree(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.borrowDisagree(twoParameter));
    }

    @ApiOperation(value = "确认借书", notes = "借书完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/borrow/confirm")
    public Result borrowConfirm(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.borrowConfirm(twoParameter));
    }

    @ApiOperation(value = "确认还书", notes = "确认还书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/return/confirm")
    public Result returnConfirm(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.returnConfirm(twoParameter));
    }

    @ApiOperation(value = "还书成功（拥有者操作）", notes = "还书成功")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/return/ok")
    public Result returnOk(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.returnOk(twoParameter));
    }

    @ApiOperation(value = "扣除押金", notes = "扣除押金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookstoreid", value = "bookstoreid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "fee", value = "扣费（必需）2位小数", required = true, dataType = "BigDecimal")
    })
    @ResponseBody
    @PostMapping("/return/fee")
    public Result returnFee(@ModelAttribute TwoParameter twoParameter) {
        return setBookstoreNull(twoService.returnFee(twoParameter));
    }

}
