<template>

    <v-card outlined>
        <v-card-title>
            DecreaseStock
        </v-card-title>

        <v-card-text>
            <Number label="Qty" v-model="value.qty" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="decreaseStock"
            >
                DecreaseStock
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'DecreaseStockCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.qty = 0;
        },
        watch: {
        },
        methods: {
            decreaseStock() {
                this.$emit('decreaseStock', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

