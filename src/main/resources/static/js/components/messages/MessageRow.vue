<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <user-link :user="message.author" size="`${size}px`">
            </user-link>
            <div class="pt-2">
                {{ message.text }}
            </div>
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions>
            <v-btn small text rounded @click="edit">Edit</v-btn>
            <v-btn small icon @click="del">
                <v-icon>
                    delete
                </v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list :comments="message.comments" :message-id="message.id"></comment-list>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex'
    import Media from 'components/media/Media.vue'
    import CommentList from '../comment/CommentList.vue'
    import UserLink from '../UserLink.vue'

    export default {
        props: ['message', 'editMessage'],
        components: {Media, CommentList, UserLink},
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style>

</style>