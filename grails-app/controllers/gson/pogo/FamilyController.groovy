package gson.pogo

import grails.rest.*

class FamilyController extends RestfulController {

    static responseFormats = ['json', 'xml']

    FamilyController() {
        super(GrandParent)
    }

    def index(Integer max) {
        log.info("index")
        params.max = Math.min(max ?: 10, 100)
        def grandParentList = GrandParent.list(fetch: [children: 'eager'])
        respond grandParentList, model: [grandParentCount: countResources()]
    }

    def index2(Integer max) {
        log.info("index2")
        def daughter = new Child2(name: 'Beatrix')
        def mom = new Parent2(name: 'Juliana', children: [daughter])
        def gramps = new GrandParent2(name: 'Wilhelmina', children: [mom])
        def grandParent2List = [gramps]

        params.max = Math.min(max ?: 10, 100)
        respond grandParent2List, model: [grandParent2Count: 1]
    }
}
