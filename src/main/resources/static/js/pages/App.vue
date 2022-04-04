<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan by khde</v-toolbar-title>
            <v-btn text v-if="profile" :disabled="$route.path === '/'" @click="showMessages">
                Messages List
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn text v-if="profile" :disabled="$route.path === '/user'" @click="showProfile">
                {{profile.name}}
            </v-btn>
            <v-btn icon href="/logout" v-if="profile">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <router-view>

            </router-view>
        </v-main>
    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import { addHandler } from 'util/ws'

    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations([
                'addMessageMutation',
                'updateMessageMutation',
                'removeMessageMutation',
                'addCommentMutation'
            ]),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/user')
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`I think evenType is unknown "${data.eventType}" `)
                    }
                } else if (data.objectType === 'COMMENT') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addCommentMutation(data.body)
                            break
                        default:
                            console.error(`I think evenType is unknown "${data.eventType}" `)
                    }
                } else {
                    console.error(`I think objectType "${data.objectType}" not in ObjectType (for socket)`)
                }
            })
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        }
    }
</script>

<style>

</style>