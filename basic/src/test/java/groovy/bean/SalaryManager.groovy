package groovy.bean

import groovy.util.logging.Slf4j

/**
 * Created by Administrator on 2017/8/1.
 * 财务经理发放工资
 */
@Slf4j
class SalaryManager {
    def giveSalary(User user) {
        def salary = user.name.length() * 100
        log.info("给${user.name}发放了${salary}元工资")
        return salary
    }
}
