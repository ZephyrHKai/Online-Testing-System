import ajax from './request'

export const getItemListById = (data = {}) => {
    return ajax('/onlinetest/page/getitemlistbyid', data, 'get');
}

export const submitScore = (data = {}) => {
    return ajax('/onlinetest/submit/addscore', data);
}