package top.panl.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: PackageType
 * Package: top.panl.rpc.enumeration
 * Description:
 *
 * @Author liupan
 * @Create 2023/4/13 21:04
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum PackageType {
    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;
}
