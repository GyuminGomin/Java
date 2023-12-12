<template>

    <v-data-table
        :headers="headers"
        :items="mypage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MypageView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "orderId", value: "orderId" },
                { text: "productId", value: "productId" },
                { text: "deliveryStatus", value: "deliveryStatus" },
                { text: "orderStatus", value: "orderStatus" },
            ],
            mypage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/mypages'))

            temp.data._embedded.mypages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.mypage = temp.data._embedded.mypages;
        },
        methods: {
        }
    }
</script>

