/**
 * Created by liqian on 2018/7/12.
 */

const getters = {
    token: state => state.user.token,
    roles: state => state.user.roles,
}

export default getters