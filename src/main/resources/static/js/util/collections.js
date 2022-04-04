
export function getIndex(list, id) {
    for (var i=0; i < list.length; i++) {
        if (list[i].id === id) {
            return id
        }
    }
    return -1
}