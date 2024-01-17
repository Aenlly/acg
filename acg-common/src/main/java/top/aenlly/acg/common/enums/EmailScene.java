package top.aenlly.acg.common.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author:
 */
@Getter
@AllArgsConstructor
public enum EmailScene {
    /**
     * 用户注册
     */
    USER_REGISTER(1, "register", "user:register", "用户注册"),
    /**
     * 密码重置
     */
    USER_RESET_PASSWORD(2, "reset", "user:resetPwd", "用户密码重置"),
    /**
     * 用户设置支付密码
     */
    SET_PAY_PASSWORD(5, "set_pay_password", "user:setPayPassword", "用户设置支付密码"),
    /**
     * 用户修改支付密码
     */
    UPDATE_PAY_PASSWORD(6, "update_pay_password", "user:updatePayPassword", "用户修改支付密码"),
    /**
     * 用户重置支付密码
     */
    RESET_PAY_PASSWORD(7, "reset_pay_password", "user:resetPayPassword", "用户重置支付密码"),
    /**
     * 忘记密码
     */
    FORGET_PASSWORD(8, "forget_password", "user:forgetPassword", "忘记密码"),

    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(EmailScene::getCode).toArray();

    /**
     * 场景编号
     */
    private final Integer code;
    /**
     * 验证场景
     */
    private final String scene;
    /**
     * 前缀
     */
    private final String prefix;
    /**
     * 描述
     */
    private final String description;


    public static EmailScene getCodeByScene(Integer scene) {
        return ArrayUtil.firstMatch(sceneEnum -> sceneEnum.getScene().equals(scene),
                values());
    }
}
